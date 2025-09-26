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
			
			//硫��� ���� �� ������ �대� �ㅼ��
			messageHelper.setFrom("3whitedog3@gmail.com","비밀번호변경안내문자메일");
			//"보낼 메일", "표시되는 이름" 
			//세부 설정은 root-context에 위치해있습니다
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body);
			mailSender.send(message);
			//
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
