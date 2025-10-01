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
@EnableAsync//비동기 처리 활성화
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
		mailService.sendMail(vo.getEmail(),"example@email.com","임시 비밀번호는 "+pwchangesend+" 입니다"); 
		//vo.getEmail()로 이메일 주소 가져옴
		//example@email.com으로 vo.getEmail()주소로 메일을 보냅니다.
		//pwchangesend는 임시 비밀번호입니다.
		out.print("이메일이 발송되었습니다.");
		return "user/find/mailsend";
		}catch(Exception e){
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('오류가 발생했습니다.");
		    writer.println("window.location.href = '/vaca/user/find/pw';");
		    writer.println("</script>");
		    writer.flush(); // 버퍼 초기화
		    return null;
		}finally{

		}
	}
	
}
