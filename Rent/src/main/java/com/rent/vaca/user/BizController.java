package com.rent.vaca.user;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rent.vaca.acco.AccoController;
import com.rent.vaca.acco.AccoService;
import com.rent.vaca.room.RoomVO;

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
	
	// 회원가입 약관동의 화면
	@RequestMapping(value = "/biz_join_terms", method = RequestMethod.GET)
	public String bizSignUpTerms() {
		return "biz/biz_join_terms";
	}
	
	// 회원가입 화면
	@RequestMapping(value = "/biz_join_form", method = RequestMethod.GET)
	public String bizSignUp(Model model,
			@RequestParam(value = "termsofuse", required = false) String termsofuse,
			@RequestParam(value = "termsover14", required = false) String termsover14,
			@RequestParam(value = "termspersonal1", required = false) String termspersonal1
			) {
		// 유효성 검사용 BizVO 값 확인
		model.addAttribute("bizVO", new BizVO());
		
		if (termsofuse == null || termsover14 == null || termspersonal1 == null) {
			return "redirect:/biz/biz_join_terms?error=missing";
		}
		
		return "biz/biz_join_form";
		
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/biz_join_finished", method = RequestMethod.POST)
	public String bizSignUp(@Valid BizVO vo, BindingResult result,
			 @RequestParam("certificateFile") MultipartFile certificateFile,
		        Model model, HttpServletRequest request
			) throws Exception {
		
		if(result.hasErrors()) {
			// 유효성 검사 실패 시 회원가입 폼으로 리턴
			return "biz/biz_join_form";
		}
		
		if (certificateFile == null || certificateFile.isEmpty()) {
	        model.addAttribute("fileError", "사업자등록증 파일을 첨부해주세요.");
	        return "biz/biz_join_form";
	    }
		
		String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/biz");

	    // 원본 파일명 얻기
	    String originalFileName = certificateFile.getOriginalFilename();
	    
	    // 타임스탬프를 추가하여 파일명 중복방지 및 언제 받은 날짜 쉽게 기억
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    
	    // DB 저장 파일명
	    String savedFileName = timeStamp + "_" + originalFileName;
	    
	    // 저장할 파일 객체 생성
	    File saveFile = new File(uploadDir, savedFileName);

	    // 실제 파일 저장
	    certificateFile.transferTo(saveFile);

	    // 저장한 파일 이름, 경로를 지정하여 vo에 세팅해서 DB에 저장
	    vo.setCertificateOriginalName(originalFileName);
	    vo.setCertificateSavedName(savedFileName);
	    
		try {
		    bizService.insertBizOne(vo); // 이메일 중복 등 예외 발생 가능
		} catch (IllegalArgumentException e) {
		    model.addAttribute("emailError", e.getMessage());
		return "biz/biz_join_form"; // 다시 가입 폼으로
		}
		
		return "biz/biz_join_finished";
	}
	
	// 이메일 중복검사
	@ResponseBody
	@RequestMapping(value = "/check-email", method = RequestMethod.GET)
	public boolean checkEmailDuplicate(@RequestParam("email") String email) {
	    int count = bizService.selectBizCntByEmail(email);
	    return count == 0; // true: 사용 가능, false: 중복
	}
	
	// 로그인
	@RequestMapping(value = "/login/biz_login", method = RequestMethod.GET)
	public String login(BizVO vo, HttpSession session) {
		
		BizVO biz = bizService.selectBizOne(vo);
		
		if(biz == null) {
			return "redirect:/login/biz_login";
		}
		
		session.setAttribute("biz", biz);
		
		return "redirect:/";
		
	}
	
	// 숙소 등록
	
	// 객실 등록 페이지
	@RequestMapping(value = "/biz_mypage_room", method = RequestMethod.GET)
	public String addRoom() {
		return "/biz/biz_mypage_room";
	}
	
	@RequestMapping(value = "/biz_mypage_room", method = RequestMethod.POST)
	public String addRoom(RoomVO vo) {
		
		
		
		return "/biz/biz_mypage_room";
	}
	
}
