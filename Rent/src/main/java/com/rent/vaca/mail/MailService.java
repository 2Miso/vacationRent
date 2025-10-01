package com.rent.vaca.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	/*
	 * @Autowired private SimpleMailMessage preConfiguredMessage;
	 */
	
	@Async
	public void sendMail(String to , String subject, String body)
	{
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8"); 
			

			messageHelper.setFrom("example@email.com","임시비밀번호전송");
			//"전달받은 상대에게 표시될 메일 주소","전달 받은 상대에게 표시될 이름"
			//root-context에서 SMTP 설정
			//참고 : https://licht-study.tistory.com/entry/Spring-메일-전송하기Gmail
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body);
			mailSender.send(message);

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
