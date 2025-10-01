package com.rent.vaca.mail;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rent.vaca.mail.MailService;
import com.rent.vaca.user.UserService;
import com.rent.vaca.user.UserVO;

@Controller
@EnableAsync//�񵿱� ó�� Ȱ��ȭ
public class MailController {
		
	@Autowired
	private MailService mailService;
	

	private final UserService userService;
	
	@Autowired
	public MailController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/user/find/pw", method= RequestMethod.POST)
	public String findPw(HttpServletRequest request,HttpServletResponse response,UserVO vo) throws Exception
	{
		try {
		userService.pwauto(vo); 
		String pwchangesend = userService.findPw(vo).getPw();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		mailService.sendMail(vo.getEmail(),"example@email.com","�ӽ� ��й�ȣ�� "+pwchangesend+" �Դϴ�"); 
		//vo.getEmail()�� �̸��� �ּ� ������
		//example@email.com���� vo.getEmail()�ּҷ� ������ �����ϴ�.
		//pwchangesend�� �ӽ� ��й�ȣ�Դϴ�.
		out.print("�̸����� �߼۵Ǿ����ϴ�.");
		return "user/find/mailsend";
		}catch(Exception e){
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('������ �߻��߽��ϴ�.");
		    writer.println("window.location.href = '/vaca/user/find/pw';");
		    writer.println("</script>");
		    writer.flush(); // ���� �ʱ�ȭ
		    return null;
		}finally{

		}
	}
	
}
