package com.rent.vaca.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String findEmail(UserVO vo,HttpServletRequest request) throws Exception{
		UserVO email = userService.findEmail(vo);
		if(email != null) {
			request.setAttribute("msg", "가입된 이메일은 #{} 입니다");
			
		}else {//일치하는 이메일이 없을 때 
			request.setAttribute("msg", "없는 회원정보입니다");
		}
		return null;
	}
	
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	

	
	
	
	//로그인 페이지
	@RequestMapping(value="/login/email", method = RequestMethod.GET) 
	public String login() {
		return "login/email";
	}//로그인 페이지 접속가능
	
	//로그인 기능구현
	@RequestMapping(value="/login/email", method = RequestMethod.POST) 
	public String login(UserVO vo ,HttpSession session) {
		System.out.println("버튼눌림");
		UserVO user = userService.login(vo);
		//logger.info("");
		
		//ㄹㄴㅇㅁㄹㄴㅇ
		System.out.println("test");
		System.out.println(vo);
		System.out.println("user객체정보");
		System.out.println(user);
		
		if(user == null) {
			System.out.println("로그인정보없음");
			return "redirect:/login/email";
			
		}
		session.setAttribute("user", user); 
		System.out.println("로그인성공,세션값");
		System.out.println();
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
