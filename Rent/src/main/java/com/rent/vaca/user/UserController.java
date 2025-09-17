package com.rent.vaca.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.vaca.user.Response;

@Controller
@RequestMapping("")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//약관동의페이지
	@RequestMapping(value="/user/join/agree", method = RequestMethod.GET)
	public String agree() {
		return "user/join/agree";
	}
	
	//회원가입 페이지
	@RequestMapping(value="/user/join/form", method = RequestMethod.GET)
	public String join() {
		return "user/join/form";
	}
	
	//회원가입 기능구현
	@RequestMapping(value="user/join/form", method = RequestMethod.POST) 
	public String join(UserVO vo) {
		System.out.println("버튼눌림");
		userService.join(vo);
		return "redirect:/user/join/finish";
	}
	
	//이메일중복체크 기능구현
	@RequestMapping(value="user/join/form/emailCheck", method = RequestMethod.POST) 
	@ResponseBody
	public Response emailCheck(@RequestParam("email") String email) {
		System.out.println("코드가 작동됨");
		int cnt = userService.emailCheck(email);
		System.out.println(cnt);
		Response res = new Response();
		res.setCode(cnt);
		res.setResult("success");
		return res;
	}
	
	//회원가입완료 페이지 
	@RequestMapping(value="/user/join/finish", method = RequestMethod.GET) 
	public String finish() {
		return "user/join/finish";
	}
	
	//이메일찾기 페이지
	@RequestMapping(value="/user/find/email", method = RequestMethod.GET) 
	public String findEmail() {
		return "user/find/email";
	}
	
	//이메일찾기페이지 기능 
	@RequestMapping(value="/user/find/email", method = RequestMethod.POST) 
	public String findEmail(UserVO vo,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		try {
			String email = userService.findEmail(vo).getEmail();
			System.out.println(userService.findEmail(vo).getEmail());
			model.addAttribute("email", email);
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('가입한 이메일은 "+email+"입니다.');");
		    writer.println("window.location.href = '/vaca/user/find/email';");
		    writer.println("</script>");
		    writer.flush(); // 버퍼 비우기

		}catch(NullPointerException e){
			model.addAttribute("email", "입력한 정보가 일치하지 않거나 없는 정보입니다");
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = response.getWriter();
		    writer.println("<script>");
		    writer.println("alert('입력한 정보가 일치하지 않거나 없는 정보입니다');");
		    writer.println("window.location.href = '/vaca/user/find/email';");
		    writer.println("</script>");
		    writer.flush(); // 버퍼 비우기

		}finally{

		}
		return null;

	}
	
	//비밀번호찾기 페이지
	@RequestMapping(value="/user/find/pw", method = RequestMethod.GET) 
	public String findPw() {
		return "user/find/pw";
	}
	
	//비밀번호찾기 메일보냈음페이지 
	@RequestMapping(value="/user/find/mailsend", method = RequestMethod.GET) 
	public String mailSend() {
		return "user/find/mailsend";
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
	

	
	
	
	//로그인 페이지
	@RequestMapping(value="/login/email", method = RequestMethod.GET) 
	public String login() {
		return "login/email";
	}//로그인 페이지 접속가능
	
	//로그인 기능구현
	@RequestMapping(value="/login/email", method = RequestMethod.POST) 
	public String login(UserVO vo ,HttpSession session,Model model,HttpServletResponse response) throws IOException {
		UserVO user = userService.login(vo);
		//로그인 실패
		if(user == null) {
			response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('비밀번호가 일치하지 않거나 없는 계정입니다.');");
		    out.println("history.back();"); // 또는 다른 페이지로 이동
		    out.println("</script>");
		    out.flush();
		    out.close();
		}
		//로그인 성공
		session.setAttribute("user", user); 
		return "redirect:/main/main";
	}
	
	//로그아웃 구현
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/user/login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//메인으로 이동시켜주는메소드
	@RequestMapping(value="main/main", method = RequestMethod.GET) 
	public String main(HttpSession session) {
		session.getAttribute("user");
		return "main/main";
	}
}
