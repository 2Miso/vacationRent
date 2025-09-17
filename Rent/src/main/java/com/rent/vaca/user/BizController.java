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
	
	//����Ͻ� ȸ�� ��Ʈ�ѷ�
	private static final Logger logger = LoggerFactory.getLogger(AccoController.class);
	
	private final BizService bizService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public BizController(BizService bizService, PasswordEncoder passwordEncoder) {
		this.bizService = bizService;
		this.passwordEncoder = passwordEncoder;
	}
	
	// ȸ������ ������� ȭ��
	@RequestMapping(value = "/biz_join_terms", method = RequestMethod.GET)
	public String bizSignUpTerms() {
		return "biz/biz_join_terms";
	}
	
	// ȸ������ ȭ��
	@RequestMapping(value = "/biz_join_form", method = RequestMethod.GET)
	public String bizSignUp(Model model,
			@RequestParam(value = "termsofuse", required = false) String termsofuse,
			@RequestParam(value = "termsover14", required = false) String termsover14,
			@RequestParam(value = "termspersonal1", required = false) String termspersonal1
			) {
		// ��ȿ�� �˻�� BizVO �� Ȯ��
		model.addAttribute("bizVO", new BizVO());
		
		if (termsofuse == null || termsover14 == null || termspersonal1 == null) {
			return "redirect:/biz/biz_join_terms?error=missing";
		}
		
		return "biz/biz_join_form";
		
	}
	
	// ȸ������ ó��
	@RequestMapping(value = "/biz_join_finished", method = RequestMethod.POST)
	public String bizSignUp(@Valid BizVO vo, BindingResult result,
			 @RequestParam("certificateFile") MultipartFile certificateFile,
		        Model model, HttpServletRequest request
			) throws Exception {
		
		if(result.hasErrors()) {
			// ��ȿ�� �˻� ���� �� ȸ������ ������ ����
			return "biz/biz_join_form";
		}
		
		if (certificateFile == null || certificateFile.isEmpty()) {
	        model.addAttribute("fileError", "����ڵ���� ������ ÷�����ּ���.");
	        return "biz/biz_join_form";
	    }
		
		String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/biz");

	    // ���� ���ϸ� ���
	    String originalFileName = certificateFile.getOriginalFilename();
	    
	    // Ÿ�ӽ������� �߰��Ͽ� ���ϸ� �ߺ����� �� ���� ���� ��¥ ���� ���
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    
	    // DB ���� ���ϸ�
	    String savedFileName = timeStamp + "_" + originalFileName;
	    
	    // ������ ���� ��ü ����
	    File saveFile = new File(uploadDir, savedFileName);

	    // ���� ���� ����
	    certificateFile.transferTo(saveFile);

	    // ������ ���� �̸�, ��θ� �����Ͽ� vo�� �����ؼ� DB�� ����
	    vo.setCertificateOriginalName(originalFileName);
	    vo.setCertificateSavedName(savedFileName);
	    
		try {
		    bizService.insertBizOne(vo); // �̸��� �ߺ� �� ���� �߻� ����
		} catch (IllegalArgumentException e) {
		    model.addAttribute("emailError", e.getMessage());
		return "biz/biz_join_form"; // �ٽ� ���� ������
		}
		
		return "biz/biz_join_finished";
	}
	
	// �̸��� �ߺ��˻�
	@ResponseBody
	@RequestMapping(value = "/check-email", method = RequestMethod.GET)
	public boolean checkEmailDuplicate(@RequestParam("email") String email) {
	    int count = bizService.selectBizCntByEmail(email);
	    return count == 0; // true: ��� ����, false: �ߺ�
	}
	
	// �α���
	@RequestMapping(value = "/login/biz_login", method = RequestMethod.GET)
	public String login(BizVO vo, HttpSession session) {
		
		BizVO biz = bizService.selectBizOne(vo);
		
		if(biz == null) {
			return "redirect:/login/biz_login";
		}
		
		session.setAttribute("biz", biz);
		
		return "redirect:/";
		
	}
	
	// ���� ���
	
	// ���� ��� ������
	@RequestMapping(value = "/biz_mypage_room", method = RequestMethod.GET)
	public String addRoom() {
		return "/biz/biz_mypage_room";
	}
	
	@RequestMapping(value = "/biz_mypage_room", method = RequestMethod.POST)
	public String addRoom(RoomVO vo) {
		
		
		
		return "/biz/biz_mypage_room";
	}
	
}
