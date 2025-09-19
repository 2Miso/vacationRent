package com.rent.vaca.user;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rent.vaca.acco.AccoController;
import com.rent.vaca.acco.AccoPhotoVO;
import com.rent.vaca.acco.AccoService;
import com.rent.vaca.acco.AccoVO;
import com.rent.vaca.room.RoomVO;

@Controller
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
	@RequestMapping(value = "/biz/biz_join_terms", method = RequestMethod.GET)
	public String bizSignUpTerms() {
		return "biz/biz_join_terms";
	}
	
	// ȸ������ ȭ��
	@RequestMapping(value = "/biz/biz_join_form", method = RequestMethod.GET)
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
	@RequestMapping(value = "/biz/biz_join_finished", method = RequestMethod.POST)
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
	@RequestMapping(value = "/biz/check-email", method = RequestMethod.GET)
	public boolean checkEmailDuplicate(@RequestParam("email") String email) {
	    int count = bizService.selectBizCntByEmail(email);
	    return count == 0; // true: ��� ����, false: �ߺ�
	}
	
	// �α��� ������
	@RequestMapping(value="/login/biz_login", method = RequestMethod.GET) 
	public String login() {
		return "login/biz_login";
	}
	
	// �α��� ó��
	@RequestMapping(value = "/login/biz_login", method = RequestMethod.POST)
	public String login(BizVO vo, HttpSession session) {
		
		BizVO biz = bizService.selectBizOne(vo);
		
		if(biz == null) {
			return "redirect:/login/biz_login";
		}
		
		session.setAttribute("biz", biz);
		
		return "redirect:/";
		
	}
	
	// ���� ��� ������
	@RequestMapping(value = "/biz/biz_mypage_acco", method = RequestMethod.GET)
	public String addAcco(HttpSession session, Model model) {
	
		// ���� �α��� ���� ��������
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
			return "redirect:/login/biz_login";
		}
		
		// ���� ���� ��ȸ
		AccoVO acco = bizService.selectBizAccoOne(biz.getId());
		
		// ���� ���� ��ȸ
		List<AccoPhotoVO> accoList = bizService.getPhotosByBizId(acco.getAccoNo());
	    model.addAttribute("accoList", accoList);
		
	    // ���� ���� ���� ��ȸ
		int count = bizService.existsAccoByBizIdAndDelyn(biz.getId(), "N");
	    
	    boolean disableInput = (count > 0);
	    
	    model.addAttribute("acco", acco);
	    model.addAttribute("biz", biz);
	    model.addAttribute("disableInput", disableInput);
	   
		return "biz/biz_mypage_acco";
	}
	
	// ���� ��� ó��
	@RequestMapping(value = "/biz/biz_mypage_acco", method = RequestMethod.POST)
	public String addAcco(BizVO vo, AccoVO vo1, Model model,
			HttpSession session, HttpServletRequest request,
			@RequestParam("image") List<MultipartFile> imageFiles
			) {
		
		try {
			
			// ���� �α��� ���� ��������
			BizVO biz = (BizVO) session.getAttribute("biz");
			
			if(biz == null) {
				// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
				return "redirect:/login/biz_login";
			}
			
			vo1.setBizId(biz.getId());
			
	    	// �������� ���
	        bizService.insertAccoOne(vo1);
	        
	        // ���� �̹��� ���
	        
	        int accoNo = vo1.getAccoNo();
	        
	        String uploadDir = request.getSession().getServletContext().getRealPath("resources/img/acco");
			File dir = new File(uploadDir);
            if(!dir.exists()) {
            	dir.mkdirs();
            }
	        
	        for (MultipartFile image : imageFiles) {
	            if (!image.isEmpty()) {
	                String originalName = image.getOriginalFilename();
	                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	                String savedName = timeStamp + "_" + originalName;

	                // �̹��� ���� ���
	                File saveFile = new File(uploadDir, savedName);
	                image.transferTo(saveFile);

	                // DB ����� VO ����
	                AccoPhotoVO photoVO = new AccoPhotoVO();
	                photoVO.setAccoNo(accoNo);
	                photoVO.setRoomNo(0);
	                photoVO.setOriginalName(originalName);
	                photoVO.setSavedName(savedName);
	         
	                // DB insert
	                bizService.insertAccoPhoto(photoVO);
	            }
	        }
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	        model.addAttribute("errorMessage", "���� ��� ����");
	        return "/biz/biz_mypage_acco";
	    }
	    
		return "redirect:/biz/biz_mypage_acco";
	}
	
	// ���� ���� ó��
	@PostMapping("/biz/edit_acco")
	public String accoEdit() {
		return "redirect:/biz/biz_mypage_acco";
	}
	
	// ���� ���� ó��
	@PostMapping("/biz/delete_acco")
	public String accoDelete() {
		return "redirect:/biz/biz_mypage_acco";
	}
	
	// ���� ��� ó��
	@PostMapping("/biz/upload_acco_photo")
	public String uploadAccoPhoto(
			@RequestParam("accoNo") int accoNo,
			@RequestParam("image") List<MultipartFile> imageFiles,
			HttpServletRequest request
			) {
		
		try {
			String uploadDir = request.getSession().getServletContext().getRealPath("resources/img/acco");
			File dir = new File(uploadDir);
            if(!dir.exists()) {
            	dir.mkdirs();
            }
			
			for (MultipartFile image : imageFiles) {
	            if (!image.isEmpty()) {
	                String originalName = image.getOriginalFilename();
	                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	                String savedName = timeStamp + "_" + originalName;
	                
	                // �̹��� ���� ���
	                File saveFile = new File(uploadDir, savedName);
	                image.transferTo(saveFile);

	                // DB ����� VO ����
	                AccoPhotoVO photoVO = new AccoPhotoVO();
	                photoVO.setAccoNo(accoNo);
	                photoVO.setRoomNo(0);
	                photoVO.setOriginalName(originalName);
	                photoVO.setSavedName(savedName);
	         
	                // DB insert
	                bizService.insertAccoPhoto(photoVO);
	            }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "/biz/biz_mypage_acco";
	}
	
	// ���� ���� ó��
	@PostMapping("/biz/delete_acco_photo")
	public String deleteAccoPhoto(HttpSession session,
			@RequestParam("accoNo") int accoNo,
			HttpServletRequest request) {
		try {
	        // DB���� ����
		
			// ���� �α��� ���� ��������
			BizVO biz = (BizVO) session.getAttribute("biz");
			
			// ������ ���� ��ȣ
			int deleteAccoNo = accoNo;
			
	        List<AccoPhotoVO> photoList = bizService.getPhotosByBizId(deleteAccoNo);

	        // ���� ���ϵ� ����
	        String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/acco");
	        
	        for (AccoPhotoVO photo : photoList) {
	            String savedName = photo.getSavedName();
	            File file = new File(uploadDir, savedName);
	            if (file.exists()) {
	                file.delete();
	            }
	        }
	        
	        bizService.deleteAccoPhotoByAccoNo(deleteAccoNo);

	        return "redirect:/biz/biz_mypage_acco";
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/biz/biz_mypage_acco?error=true";
	    }
	}
	
	// ���� ��� ������
	@RequestMapping(value = "/biz/biz_mypage_room", method = RequestMethod.GET)
	public String addRoom(Model model, HttpSession session,
			BizVO vo, RoomVO vo1) {
		/*
		// ���� �α��� ���� ��������
		BizVO biz = (BizVO) session.getAttribute("id");
		if(biz == null) {
			// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
			return "redirect:/login/biz_login";
		}
		
		// �α����� ����Ͻ� ȸ���� �������� ��������
	    int bizId = biz.getId();
	    
	    Integer accoNo = bizService.selectBizCntByAccoNo(bizId);
	   
	    if(accoNo == null || accoNo == 0){
	        model.addAttribute("errorMessage", "���Ҹ� ���� ����ؾ� ������ ����� �� �ֽ��ϴ�.");
	        return "redirect:/biz/biz_mypage_acco";
	    }
	    
	    vo1.setAccoNo(accoNo);
		*/
		return "/biz/biz_mypage_room";
	}
	
	// ���� ��� ó�� ������
	@RequestMapping(value = "/biz/biz_mypage_room", method = RequestMethod.POST)
	public String addRoom(RoomVO vo, Model model,
			@RequestParam("image[]") List<MultipartFile> imageFiles,
			HttpServletRequest request
			) throws Exception {

	    try {
	    	// �������� ���
	        bizService.insertRoomOne(vo);
	        
	        int roomNo = bizService.selectLastInsertedRoomNo(vo.getRoomNo());
	        
	        for (MultipartFile image : imageFiles) {
	            if (!image.isEmpty()) {
	                String originalName = image.getOriginalFilename();
	                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	                String savedName = timeStamp + "_" + originalName;

	                // �̹��� ���� ���
	                String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/room");
	                File saveFile = new File(uploadDir, savedName);
	                image.transferTo(saveFile);

	                // DB ����� VO ����
	                AccoPhotoVO photoVO = new AccoPhotoVO();
	                photoVO.setRoomNo(roomNo);
	                photoVO.setOriginalName(originalName);
	                photoVO.setSavedName(savedName);

	                // DB insert
	                bizService.insertRoomPhoto(photoVO);
	            }
	        }
	        
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "���� ��� ����");
	        return "/biz/biz_mypage_room";
	    }
	    
		return "redirect:/biz/biz_mypage_room";
	}
	
}
