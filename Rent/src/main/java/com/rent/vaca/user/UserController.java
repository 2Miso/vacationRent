package com.rent.vaca.user;

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
	
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//로그인 페이지
	@RequestMapping(value="login/emaillogin", method = RequestMethod.GET) 
	public String login() {
		return "login/emaillogin";
	}
	//로그인 기능구현
	@RequestMapping(value="login/emaillogin", method = RequestMethod.POST) 
	public String login(UserVO vo ,HttpSession session) {

		UserVO user = userService.login(vo);
		//logger.info("");
		
		
		System.out.println("test");
		System.out.println(vo);
		System.out.println("user객체정보");
		System.out.println(user);
		
		if(user == null) {
			System.out.println("로그인정보없음");
			return "redirect:/login/emaillogin";
			
		}
		session.setAttribute("user", user); 
		System.out.println("로그인성공,세션값");
		System.out.println();
		return "redirect:/main/main";
	}
	
	//로그아웃기능메서드
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
