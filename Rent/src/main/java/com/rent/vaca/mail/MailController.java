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
@EnableAsync//鍮���湲곕� ������寃� ���� �대�명���댁��
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
		System.out.println(userService.findPw(vo).getPw());
		String pwchangesend = userService.findPw(vo).getPw();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("���ㅽ�몄��");
		PrintWriter out = response.getWriter();
		mailService.sendMail("sleepless0527@gmail.com","비밀번호 변경 안내","변경된 비밀번호는"+pwchangesend+"입니다"); 
		//설명 ("전달할 메일주소","메일제목","메일내용"
		//다 깨졋습니다
		//제 개인메일입니다.
		out.print("硫��� ���� ��猷�");
		return "user/find/mailsend";
		}catch(Exception e){
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('정보를 입력해 주세요');");
		    writer.println("window.location.href = '/vaca/user/find/pw';");
		    writer.println("</script>");
		    writer.flush(); // 踰��� 鍮��곌린
		    return null;
		}finally{

		}
	}
	
}
