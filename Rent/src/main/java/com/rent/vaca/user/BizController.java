package com.rent.vaca.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rent.vaca.acco.AccoController;
import com.rent.vaca.acco.AccoService;

@Controller
@RequestMapping("/biz")
public class BizController {
	
	//비즈니스 회원 컨트롤러
	private static final Logger logger = LoggerFactory.getLogger(AccoController.class);
	
	private final BizService bizService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public BizController(BizService bizService, PasswordEncoder passwordEncoder) {
		this.bizService = bizService;
		this.passwordEncoder = passwordEncoder;
	}
	
	// 회원가입 화면
	@RequestMapping(value = "/biz_join_form", method = RequestMethod.GET)
	public String bizSignUp() {
		return "biz/biz_join_form";
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String bizSignUp(BizVO vo) {
		
		bizService.insertBizOne(vo);
		return "biz/signup";
	}
}
