package com.rent.vaca.mail;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rent.vaca.mail.MailService;

@Controller
@EnableAsync//비동기로 동작하게 하는 어노테이션
public class MailController {
		
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/user/find/pw", method= RequestMethod.POST)
	public String findPw(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("테스트용");
		PrintWriter out = response.getWriter();
		mailService.sendMail("sleepless0527@gmail.com","테스트용 메일 제목","메일 내용이 잘 도착했는지 테스트"); //개인메일주소테스트용...
		out.print("메일 전송 완료");
		return "user/find/mailsend";
		
	}
	
}
