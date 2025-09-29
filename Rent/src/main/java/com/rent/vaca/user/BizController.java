package com.rent.vaca.user;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@RequestMapping(value = "/join/biz_join_terms", method = RequestMethod.GET)
	public String bizSignUpTerms() {
		return "/join/biz_join_terms";
	}
	
	// ȸ������ ȭ��
	@RequestMapping(value = "/join/biz_join_form", method = RequestMethod.GET)
	public String bizSignUp(Model model,
			@RequestParam(value = "termsofuse", required = false) String termsofuse,
			@RequestParam(value = "termsover14", required = false) String termsover14,
			@RequestParam(value = "termspersonal1", required = false) String termspersonal1
			) {
		// ��ȿ�� �˻�� BizVO �� Ȯ��
		model.addAttribute("bizVO", new BizVO());
		
		if (termsofuse == null || termsover14 == null || termspersonal1 == null) {
			return "redirect:/join/biz_join_terms?error=missing";
		}
		
		return "/join/biz_join_form";
		
	}
	
	// ȸ������ ó��
	@RequestMapping(value = "/join/biz_join_finished", method = RequestMethod.POST)
	public String bizSignUp(@Valid BizVO vo, BindingResult result,
			 @RequestParam("certificateFile") MultipartFile certificateFile,
		        Model model, HttpServletRequest request
			) throws Exception {
		
		if(result.hasErrors()) {
			// ��ȿ�� �˻� ���� �� ȸ������ ������ ����
			return "/join/biz_join_form";
		}
		
		if (certificateFile == null || certificateFile.isEmpty()) {
	        model.addAttribute("fileError", "����ڵ���� ������ ÷�����ּ���.");
	        return "/join/biz_join_form";
	    }
		
		String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/biz");
		
		File dir = new File(uploadDir);
		if (!dir.exists()) {
		    dir.mkdirs(); // ���丮 ������ ����
		}
		
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
		return "/join/biz_join_form"; // �ٽ� ���� ������
		}
		
		return "/join/biz_join_finished";
	}
	
	// �̸��� �ߺ��˻�
	@ResponseBody
	@RequestMapping(value = "/join/check-email", method = RequestMethod.GET)
	public boolean checkEmailDuplicate(@RequestParam("email") String email) {
	    int count = bizService.selectBizCntByEmail(email);
	    return count == 0; // true: ��� ����, false: �ߺ�
	}
	
	// �α��� ������
	@RequestMapping(value="/login/biz_login", method = RequestMethod.GET) 
	public String login() {
		return "/login/biz_login";
	}
	
	// �α��� ó��
	@RequestMapping(value = "/login/biz_login", method = RequestMethod.POST)
	public String login(BizVO vo, HttpSession session) {
		
		BizVO biz = bizService.selectBizOne(vo);
		
		if(biz == null) {
			return "redirect:/login/biz_login";
		}
		
		AccoVO acco = bizService.selectBizAccoOne(biz.getId());
			
		session.setAttribute("biz", biz);
		session.setAttribute("acco", acco);
	
		return "redirect:/";
		
	}
	// ��й�ȣ ���� ������
	@GetMapping("biz/biz_pw_change_form")
	public String bizPwChange(HttpSession session) {
		
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			return "redirect:/login/biz_login";
		}
		
		return "/biz/biz_pw_change_form";
	}
	// ��й�ȣ ���� ó��
	@PostMapping("/biz/biz_pw_change")
	public String bizPwChange(
			BizPwChangeVO vo, HttpSession session) {
		
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			return "redirect:/login/biz_login";
		}
		
		boolean success = bizService.bizPwChange(biz.getEmail(), vo);
		
		if(success) {
			return "redirect:/biz/biz_mypage_account?pwChange=success";
		}else {
			return "redirect:/biz/biz_mypage_account?pwChange=fail";
		}
	}
	
	// ���� ��� ������
	@RequestMapping(value = "/biz/biz_mypage_acco", method = RequestMethod.GET)
	public String addAcco(
			HttpServletRequest request, HttpSession session, Model model, BizVO vo) {
		
		// ���� �α��� ���� ��������
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
			return "redirect:/login/biz_login";
		}

		// ���� ���� ��ȸ
		AccoVO acco = bizService.selectBizAccoOne(biz.getId());

		// ���� ���� ��ȸ
		if(acco != null) {
			List<AccoPhotoVO> accoList = bizService.getPhotosByBizId(acco.getAccoNo());
		    model.addAttribute("accoList", accoList);
		}
		
	    // ���� ���� ���� ��ȸ
		int count = bizService.existsAccoByBizIdAndDelyn(biz.getId(), "N");
	    
	    boolean disableInput = (count > 0);
	    
	    model.addAttribute("acco", acco);
	    model.addAttribute("biz", biz);
	    model.addAttribute("disableInput", disableInput);
	   
		return "/biz/biz_mypage_acco";
	}
	
	// ���� ��� ó��
	@RequestMapping(value = "/biz/biz_mypage_acco", method = RequestMethod.POST)
	public String addAcco(AccoVO vo, Model model,
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
			
			vo.setBizId(biz.getId());
			
	    	// �������� ���
	        bizService.insertAccoOne(vo);
	        
	        // ���� �̹��� ���
	        
	        int accoNo = vo.getAccoNo();
	        
	        String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/acco");
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
	    
		session.setAttribute("acco", vo);
		
		return "redirect:/biz/biz_mypage_acco";
	}
	
	// ���� ���� ó��
	@PostMapping("/biz/edit_acco")
	public String accoEdit(
			@ModelAttribute AccoVO vo, HttpSession session, HttpServletRequest request,
	        @RequestParam(value = "image", required = false) List<MultipartFile> imageFiles,
	        Model model             
			) {

		// ���� �α��� ���� ��������
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
			return "redirect:/login/biz_login";
		}
		
	    // ���� ���� ���� �ҷ�����
	    AccoVO existingAcco = bizService.selectBizAccoOne(vo.getBizId());
	    if (existingAcco == null) {
	        // �������� ������ ���� ó��
	        return "redirect:/biz/biz_mypage_acco?error=notfound";
	    }	    
	    
	    // Ŭ���̾�Ʈ���� ���޵��� ���� �ʵ�� ���� �� ����
	    if (!StringUtils.hasText(vo.getName())) vo.setName(existingAcco.getName());
	    if (!StringUtils.hasText(vo.getAddr())) vo.setAddr(existingAcco.getAddr());
	    if (!StringUtils.hasText(vo.getPhone())) vo.setPhone(existingAcco.getPhone());
	    if (!StringUtils.hasText(vo.getDescription())) vo.setDescription(existingAcco.getDescription());
	    if (!StringUtils.hasText(vo.getBizHour())) vo.setBizHour(existingAcco.getBizHour());
	    if (!StringUtils.hasText(vo.getCheckin())) vo.setCheckin(existingAcco.getCheckin());
	    if (!StringUtils.hasText(vo.getCheckout())) vo.setCheckout(existingAcco.getCheckout());

	    // �̹��� ó��
	    
	    int accoNo = vo.getAccoNo();
	    
	    List<AccoPhotoVO> existingPhotos = bizService.getPhotosByBizId(accoNo);
	    
	    if (imageFiles != null && !imageFiles.isEmpty() && imageFiles.get(0).isEmpty()) {
	        // �� �̹����� �ִٸ� ���ε� ó��
	    	
	    	if(existingPhotos != null && !existingPhotos.isEmpty()) {
	    		model.addAttribute("errorMessage", "���� ���� ������ �����մϴ�. ������ ���� �������ּ���.");
	    		model.addAttribute("acco", existingAcco);
	    		return "/biz/biz_mypage_acco";
	    	}
	    	
	    	String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/acco");
	    	File dir = new File(uploadDir);
	    	if (!dir.exists()) {
	    	    dir.mkdirs();
	    	}
	    	for (MultipartFile image : imageFiles) {
	            if (!image.isEmpty()) {
	                String originalName = image.getOriginalFilename();
	                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	                String savedName = timeStamp + "_" + originalName;

	                // �̹��� ���� ���
	                File saveFile = new File(uploadDir, savedName);
	                try {
	                    image.transferTo(saveFile);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    throw new RuntimeException("�̹��� ���� ����", e);
	                }

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
	    }else {
	        // �� ������ ���µ� ���� ������ ���ٸ� ��� ó��
	        if (existingPhotos == null || existingPhotos.isEmpty()) {
	            model.addAttribute("errorMessage", "���� ������ �� �� �̻� ���ε����ּ���.");
	            model.addAttribute("acco", existingAcco);
	            return "/biz/biz_mypage_acco";
	        }
	    }
	    // ���� ���� ������Ʈ
	    bizService.updateAccoInfo(vo);

	    session.setAttribute("acco", vo);
	    
	    return "redirect:/biz/biz_mypage_acco?success=edit";
	}
	
	// ���� ���� ó��
	@PostMapping("/biz/delete_acco")
	public String accoDelete(Model model,
			@RequestParam("accoNo") int accoNo) {
		bizService.deleteAccoDelyn(accoNo);
		
		model.addAttribute("acco", new AccoVO());
		
		return "/biz/biz_mypage_acco";
	}
	
	// ���� ���� ��� ó��
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
	
	// ���� ���� ���� ó��
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

	        return "/biz/biz_mypage_acco";
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/biz/biz_mypage_acco?error=true";
	    }
	}
	
	// ���� ��� ������
	@RequestMapping(value = "/biz/biz_mypage_room", method = RequestMethod.GET)
	public String addRoom(Model model, HttpSession session, RoomVO vo
		) {
		
		// ���� �α��� ���� ��������
		BizVO biz = (BizVO) session.getAttribute("biz");
		if(biz == null) {
			// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
			return "redirect:/login/biz_login";
		}
		
		// �α����� ����Ͻ� ȸ���� �������� ��������
	    AccoVO acco = (AccoVO) session.getAttribute("acco");
	    if (acco == null) {
	        // ���� ������ ���� ��� ���
	    	model.addAttribute("errorMessage", "���Ҹ� ���� ����ؾ� ������ ����� �� �ֽ��ϴ�.");
	        return "redirect:/biz/biz_mypage_acco";
	    }
	    
	    Integer accoNo = acco.getAccoNo();
	    List<RoomVO> rooms = bizService.selectRoomsByAccoNo(accoNo);
	    for(RoomVO r : rooms){
	        List<AccoPhotoVO> photos = bizService.getPhotosByBizIdAndRoomNo(accoNo, r.getRoomNo());
	        r.setPhotoList(photos);
	    }
	    model.addAttribute("rooms", rooms);
	    
	    RoomVO room;
	    
	    if(vo == null) {
	    	room = new RoomVO();
	    }else {
	    	room = vo;
	    }
	    
	    Integer roomNo = room.getRoomNo();
	    
	    List<AccoPhotoVO> photoList = Collections.emptyList();
	    
	    // �������� ��������
	    
	    if (roomNo != null) {
	    	RoomVO dbRoom = bizService.selectAccoRoomOne(roomNo);
		    if(dbRoom != null) {
		    	room = dbRoom;
		    	photoList = bizService.getPhotosByBizIdAndRoomNo(accoNo, roomNo);
		    	room.setPhotoList(photoList);
		    }else {
		    	room = new RoomVO();
		    	room.setAccoNo(accoNo);
		    }
	    	
		    
	    }
	    
	    
	    model.addAttribute("room", room);
	    model.addAttribute("photoList", photoList);
	    
		return "/biz/biz_mypage_room";
	}
	
	// ���� ��� ó�� ������
	@RequestMapping(value = "/biz/biz_mypage_room", method = RequestMethod.POST)
	public String addRoom(RoomVO vo, Model model, HttpSession session,
			@RequestParam("image") List<MultipartFile> imageFiles,
			HttpServletRequest request
			) throws Exception {

	    try {
	    	
	    	// ���� �α��� ���� ��������
			BizVO biz = (BizVO) session.getAttribute("biz");
			
			if(biz == null) {
				// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
				return "redirect:/login/biz_login";
			}
			
			// �α����� ����Ͻ� ȸ���� �������� ��������
		    AccoVO acco = (AccoVO) session.getAttribute("acco");
		    
		    Integer accoNo = acco.getAccoNo();
		    
		    if(accoNo == 0 || accoNo == null){
		        model.addAttribute("errorMessage", "���Ҹ� ���� ����ؾ� ������ ����� �� �ֽ��ϴ�.");
		        return "redirect:/biz/biz_mypage_acco";
		    }
	    	
	    	// ���� ���� ���
		    
		    vo.setAccoNo(accoNo);
	
	        bizService.insertRoomOne(vo);
	        
		    Integer roomNo = vo.getRoomNo();
	        
	        // ���� ���� ���
	        
	        String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/acco");
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
	                photoVO.setRoomNo(roomNo);
	                photoVO.setOriginalName(originalName);
	                photoVO.setSavedName(savedName);

	                // DB insert
	                bizService.insertRoomPhoto(photoVO);
	            }
	        }
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	        model.addAttribute("errorMessage", "���� ��� ����");
	        return "/biz/biz_mypage_room";
	    }
	    
	    session.setAttribute("room", vo);
	    
		return "redirect:/biz/biz_mypage_room";
	}
	
	// ���� ���� ó��
	@PostMapping("/biz/biz_room_delete")
	public String deleteRoomPhoto(HttpSession session,
			@RequestParam("roomNo") int roomNo,
			HttpServletRequest request) {
		try {
	        // DB���� ����
			// ���� ��ȣ
			// �α����� ����Ͻ� ȸ���� �������� ��������
		    AccoVO acco = (AccoVO) session.getAttribute("acco");
			int accoNo = acco.getAccoNo();
			
	        List<AccoPhotoVO> photoList = bizService.getPhotosByBizIdAndRoomNo(accoNo, roomNo);

	        // ���� ���ϵ� ����
	        String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/acco");
	        
	        for (AccoPhotoVO photo : photoList) {
	            String savedName = photo.getSavedName();
	            File file = new File(uploadDir, savedName);
	            if (file.exists()) {
	                file.delete();
	            }
	        }
	        
	        bizService.deleteRoomByAccoNo(accoNo, roomNo);

	        return "redirect:/biz/biz_mypage_room";
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/biz/biz_mypage_room?error=true";
	    }
	}
	
	// ���� ���� ������
	@GetMapping("/biz/biz_mypage_account")
	public String editBizInfo(HttpSession session, Model model) {
		BizVO biz = (BizVO) session.getAttribute("biz");
        if (biz == null) {
            return "redirect:/login/biz_login"; // �α��� �� �Ǿ� ������ �����̷�Ʈ
        }

        model.addAttribute("biz", biz);
        return "biz/biz_mypage_account";
	}
	
	// ���� ���� ó�� ������
	@PostMapping("/biz/biz_mypage_account")
	public String editBizInfo(@Valid @ModelAttribute BizVO vo, BindingResult bindingResult,
            @RequestParam(value = "certificateFile", required = false) MultipartFile file,
            HttpSession session, Model model,
            @RequestParam(value = "action", required = false) String action,
            HttpServletRequest request) throws Exception {
		
		// ���� �α��� ���� ��������
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			// �α��� �Ǿ����� ������ �α����������� �����̷�Ʈ
			return "redirect:/login/biz_login";
		}
		
		if ("delete".equals(action)) {
	        // ���� ó��
	        String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/biz");
	        File oldFile = new File(uploadDir, biz.getCertificateSavedName());
	        if (oldFile.exists()) oldFile.delete();

	        biz.setCertificateSavedName(null);
	        biz.setCertificateOriginalName(null);
	        bizService.updateCertificateDeleted(biz.getId());
	        session.setAttribute("biz", biz);

	        return "redirect:/biz/biz_mypage_account"; // ���� �� �ٽ� �������� ��������
	    }
		
		try {
            // ���� �ʼ� üũ
            if((file == null || file.isEmpty()) && (biz.getCertificateSavedName() == null || biz.getCertificateSavedName().isEmpty())) {
                model.addAttribute("errorMessage", "����ڵ���� �̹����� �ݵ�� ���ε����ּ���.");
                return "/biz/biz_mypage_account";
            }

            // ���� ���ε�
            if(file != null && !file.isEmpty()) {
                String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/biz");
                File dir = new File(uploadDir);
                if(!dir.exists()) {
                	dir.mkdirs();
                }

                String originalName = file.getOriginalFilename();
                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String savedName = timeStamp + "_" + originalName;

                File saveFile = new File(uploadDir, savedName);
                file.transferTo(saveFile);

                // ���� ������ ������ ����
                if(biz.getCertificateSavedName() != null) {
                    File oldFile = new File(uploadDir, biz.getCertificateSavedName());
                    if(oldFile.exists()) oldFile.delete();
                }

                vo.setCertificateSavedName(savedName);
                vo.setCertificateOriginalName(originalName);
            } else {
                // ���� ���� ����
                vo.setCertificateSavedName(biz.getCertificateSavedName());
                vo.setCertificateOriginalName(biz.getCertificateOriginalName());
            }

            vo.setId(biz.getId());
            
            int updated = bizService.updateBizInfo(vo);
            if(updated == 0) {
            	model.addAttribute("errorMessage", "������ �����߽��ϴ�.");
            	return "/biz/biz_mypage_account";
            }

            // ���� ������Ʈ
            session.setAttribute("biz", vo);

        } catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "���� ����");
            return "/biz/biz_mypage_account";
        }

        return "redirect:/biz/biz_mypage_account?success=edit";
	}
	
	@PostMapping("/biz/deleteCertificate")
	@ResponseBody
	public String deleteCertificate(@RequestParam("id") Integer id,
			HttpSession session) {
	    
		BizVO biz = (BizVO) session.getAttribute("biz");
	    if(biz == null || id == null) {
	        return "redirect:/login/biz_login";
	    }

	    try {
	        // ���� �̹��� ���� ����
	        String uploadDir = session.getServletContext().getRealPath("/resources/img/biz");
	        File file = new File(uploadDir, biz.getCertificateSavedName());
	        if(file.exists()) file.delete();

	        // DB �÷� �ʱ�ȭ
	        bizService.updateCertificateDeleted(id);

	        // session ������ �ʱ�ȭ
	        biz.setCertificateSavedName(null);
	        session.setAttribute("biz", biz);

	        return "redirect:/biz/biz_mypage_account";
	    } catch(Exception e) {
	        e.printStackTrace();
	        return "/biz/biz_mypage_account?error=true";
	    }
	}
	
}


