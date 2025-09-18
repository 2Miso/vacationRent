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
@EnableAsync//비동기로 동작하게 하는 어노테이션
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
		System.out.println("테스트용");
		PrintWriter out = response.getWriter();
		mailService.sendMail("sleepless0527@gmail.com","테스트용 메일 제목","비밀번호는"+pwchangesend+"입니다"); //개인메일주소테스트용...
		//보낼 메일 주소는 userService.findPw(vo).getEmail()에서 가져오면됩니다
		out.print("메일 전송 완료");
		return "user/find/mailsend";
		}catch(Exception e){
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('입력한 정보가 일치하지 않거나 없는 정보입니다');");
		    writer.println("window.location.href = '/vaca/user/find/pw';");
		    writer.println("</script>");
		    writer.flush(); // 버퍼 비우기
		    return null;
		}finally{

		}
	}
	
}
