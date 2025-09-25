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
	@RequestMapping(value = "/biz/biz_join_terms", method = RequestMethod.GET)
	public String bizSignUpTerms() {
		return "biz/biz_join_terms";
	}
	
	// 회원가입 화면
	@RequestMapping(value = "/biz/biz_join_form", method = RequestMethod.GET)
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
	@RequestMapping(value = "/biz/biz_join_finished", method = RequestMethod.POST)
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
	@RequestMapping(value = "/biz/check-email", method = RequestMethod.GET)
	public boolean checkEmailDuplicate(@RequestParam("email") String email) {
	    int count = bizService.selectBizCntByEmail(email);
	    return count == 0; // true: 사용 가능, false: 중복
	}
	
	// 로그인 페이지
	@RequestMapping(value="/login/biz_login", method = RequestMethod.GET) 
	public String login() {
		return "login/biz_login";
	}
	
	// 로그인 처리
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
	
	// 숙소 등록 페이지
	@RequestMapping(value = "/biz/biz_mypage_acco", method = RequestMethod.GET)
	public String addAcco(
			HttpServletRequest request, HttpSession session, Model model, BizVO vo) {
		
		// 현재 로그인 정보 가져오기
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			// 로그인 되어있지 않으면 로그인페이지로 리다이렉트
			return "redirect:/login/biz_login";
		}

		// 숙소 정보 조회
		AccoVO acco = bizService.selectBizAccoOne(biz.getId());

		// 숙소 사진 조회
		if(acco != null) {
			List<AccoPhotoVO> accoList = bizService.getPhotosByBizId(acco.getAccoNo());
		    model.addAttribute("accoList", accoList);
		}
		
	    // 숙소 삭제 여부 조회
		int count = bizService.existsAccoByBizIdAndDelyn(biz.getId(), "N");
	    
	    boolean disableInput = (count > 0);
	    
	    model.addAttribute("acco", acco);
	    model.addAttribute("biz", biz);
	    model.addAttribute("disableInput", disableInput);
	   
		return "biz/biz_mypage_acco";
	}
	
	// 숙소 등록 처리
	@RequestMapping(value = "/biz/biz_mypage_acco", method = RequestMethod.POST)
	public String addAcco(AccoVO vo, Model model,
			HttpSession session, HttpServletRequest request,
			@RequestParam("image") List<MultipartFile> imageFiles
			) {
		
		try {
			
			// 현재 로그인 정보 가져오기
			BizVO biz = (BizVO) session.getAttribute("biz");
			
			if(biz == null) {
				// 로그인 되어있지 않으면 로그인페이지로 리다이렉트
				return "redirect:/login/biz_login";
			}
			
			vo.setBizId(biz.getId());
			
	    	// 숙소정보 등록
	        bizService.insertAccoOne(vo);
	        
	        // 숙소 이미지 등록
	        
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

	                // 이미지 저장 경로
	                File saveFile = new File(uploadDir, savedName);
	                image.transferTo(saveFile);

	                // DB 저장용 VO 세팅
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
	        model.addAttribute("errorMessage", "숙소 등록 실패");
	        return "/biz/biz_mypage_acco";
	    }
	    
		return "redirect:/biz/biz_mypage_acco";
	}
	
	// 숙소 수정 처리
	@PostMapping("/biz/edit_acco")
	public String accoEdit(
			@ModelAttribute AccoVO vo, HttpSession session, HttpServletRequest request,
	        @RequestParam(value = "image", required = false) List<MultipartFile> imageFiles,
	        Model model             
			) {

		// 현재 로그인 정보 가져오기
		BizVO biz = (BizVO) session.getAttribute("biz");
		
		if(biz == null) {
			// 로그인 되어있지 않으면 로그인페이지로 리다이렉트
			return "redirect:/login/biz_login";
		}
		
	    // 기존 숙소 정보 불러오기
	    AccoVO existingAcco = bizService.selectBizAccoOne(vo.getBizId());
	    System.out.println(existingAcco);
	    if (existingAcco == null) {
	        // 존재하지 않으면 실패 처리
	        return "redirect:/biz/biz_mypage_acco?error=notfound";
	    }	    
	    
	    // 2. 클라이언트에서 전달되지 않은 필드는 기존 값 유지
	    if (!StringUtils.hasText(vo.getName())) {
	    	vo.setName(existingAcco.getName());
	    }
	    if (!StringUtils.hasText(vo.getName())) vo.setName(existingAcco.getName());
	    if (!StringUtils.hasText(vo.getAddr())) vo.setAddr(existingAcco.getAddr());
	    if (!StringUtils.hasText(vo.getPhone())) vo.setPhone(existingAcco.getPhone());
	    if (!StringUtils.hasText(vo.getDescription())) vo.setDescription(existingAcco.getDescription());
	    if (!StringUtils.hasText(vo.getBizHour())) vo.setBizHour(existingAcco.getBizHour());
	    if (!StringUtils.hasText(vo.getCheckin())) vo.setCheckin(existingAcco.getCheckin());
	    if (!StringUtils.hasText(vo.getCheckout())) vo.setCheckout(existingAcco.getCheckout());

	    // 이미지 처리
	    
	    int accoNo = vo.getAccoNo();
	    
	    List<AccoPhotoVO> existingPhotos = bizService.getPhotosByBizId(accoNo);
	    
	    if (imageFiles != null && !imageFiles.isEmpty() && imageFiles.get(0).isEmpty()) {
	        // 새 이미지가 있다면 업로드 처리
	    	
	    	if(existingPhotos != null && !existingPhotos.isEmpty()) {
	    		model.addAttribute("errorMessage", "기존 숙소 사진이 존재합니다. 사진을 먼저 삭제해주세요.");
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

	                // 이미지 저장 경로
	                File saveFile = new File(uploadDir, savedName);
	                try {
	                    image.transferTo(saveFile);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    throw new RuntimeException("이미지 저장 실패", e);
	                }

	                // DB 저장용 VO 세팅
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
	        // 새 사진이 없는데 기존 사진도 없다면 경고 처리
	        if (existingPhotos == null || existingPhotos.isEmpty()) {
	            model.addAttribute("errorMessage", "숙소 사진을 한 장 이상 업로드해주세요.");
	            model.addAttribute("acco", existingAcco);
	            return "/biz/biz_mypage_acco";
	        }
	    }
	    // 숙소 정보 업데이트
	    bizService.updateAccoInfo(vo);

	    return "redirect:/biz/biz_mypage_acco?success=edit";
	}
	
	// 숙소 삭제 처리
	@PostMapping("/biz/delete_acco")
	public String accoDelete(@RequestParam("accoNo") int accoNo) {
		bizService.deleteAccoDelyn(accoNo);
		return "redirect:/biz/biz_mypage_acco";
	}
	
	// 숙소 사진 등록 처리
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
	                
	                // 이미지 저장 경로
	                File saveFile = new File(uploadDir, savedName);
	                image.transferTo(saveFile);

	                // DB 저장용 VO 세팅
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
	
	// 숙소 사진 삭제 처리
	@PostMapping("/biz/delete_acco_photo")
	public String deleteAccoPhoto(HttpSession session,
			@RequestParam("accoNo") int accoNo,
			HttpServletRequest request) {
		try {
	        // DB에서 삭제
		
			// 현재 로그인 정보 가져오기
			BizVO biz = (BizVO) session.getAttribute("biz");
			
			// 삭제할 숙소 번호
			int deleteAccoNo = accoNo;
			
	        List<AccoPhotoVO> photoList = bizService.getPhotosByBizId(deleteAccoNo);

	        // 실제 파일도 삭제
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
	
	// 객실 등록 페이지
	@RequestMapping(value = "/biz/biz_mypage_room", method = RequestMethod.GET)
	public String addRoom(Model model, HttpSession session, RoomVO vo, Integer roomNo
		) {
		
		// 현재 로그인 정보 가져오기
		BizVO biz = (BizVO) session.getAttribute("biz");
		if(biz == null) {
			// 로그인 되어있지 않으면 로그인페이지로 리다이렉트
			return "redirect:/login/biz_login";
		}
		
		// 로그인한 비즈니스 회원의 숙소정보 가져오기
	    AccoVO acco = (AccoVO) session.getAttribute("acco");
	    if (acco == null) {
	        // 숙소 정보도 없는 경우 대비
	    	model.addAttribute("errorMessage", "숙소를 먼저 등록해야 객실을 등록할 수 있습니다.");
	        return "redirect:/biz/biz_mypage_acco";
	    }
	    
	    Integer accoNo = acco.getAccoNo();
	    List<RoomVO> rooms = bizService.selectRoomsByAccoNo(accoNo);
	    model.addAttribute("rooms", rooms);
	    RoomVO room;
	    List<AccoPhotoVO> photoList = Collections.emptyList();
	    
	    // 객실정보 가져오기
	    // 객실정보가 있으면
	    if (roomNo != null) {
	    	room = bizService.selectAccoRoomOne(roomNo);
		    if(room == null){
		        room = new RoomVO();
		        room.setAccoNo(accoNo);
		    }else {
		    	photoList = bizService.getPhotosByBizIdAndRoomNo(accoNo, roomNo);
		    }
	    }else {
			room = new RoomVO();
			room.setAccoNo(accoNo);
	    }
	    
	    model.addAttribute("room", room);
	    model.addAttribute("photoList", photoList);
	    
		return "/biz/biz_mypage_room";
	}
	
	// 객실 등록 처리 페이지
	@RequestMapping(value = "/biz/biz_mypage_room", method = RequestMethod.POST)
	public String addRoom(RoomVO vo, Model model, HttpSession session,
			@RequestParam("image[]") List<MultipartFile> imageFiles,
			HttpServletRequest request
			) throws Exception {

	    try {
	    	
	    	// 현재 로그인 정보 가져오기
			BizVO biz = (BizVO) session.getAttribute("biz");
			
			if(biz == null) {
				// 로그인 되어있지 않으면 로그인페이지로 리다이렉트
				return "redirect:/login/biz_login";
			}
			
			// 로그인한 비즈니스 회원의 숙소정보 가져오기
		    AccoVO acco = (AccoVO) session.getAttribute("acco");
		    
		    Integer accoNo = acco.getAccoNo();
		    
		    if(accoNo == 0 || accoNo == null){
		        model.addAttribute("errorMessage", "숙소를 먼저 등록해야 객실을 등록할 수 있습니다.");
		        return "redirect:/biz/biz_mypage_acco";
		    }
	    	
	    	// 객실 정보 등록
		    
		    vo.setAccoNo(accoNo);
	
	        bizService.insertRoomOne(vo);
	        
		    Integer roomNo = vo.getRoomNo();
	        
	        // 객실 사진 등록
	        
	        String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/room");
	        File dir = new File(uploadDir);
            if(!dir.exists()) {
            	dir.mkdirs();
            }
	        
	        for (MultipartFile image : imageFiles) {
	            if (!image.isEmpty()) {
	                String originalName = image.getOriginalFilename();
	                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	                String savedName = timeStamp + "_" + originalName;

	                // 이미지 저장 경로
	                File saveFile = new File(uploadDir, savedName);
	                image.transferTo(saveFile);

	                // DB 저장용 VO 세팅
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
	        model.addAttribute("errorMessage", "객실 등록 실패");
	        return "/biz/biz_mypage_room";
	    }
	    
		return "redirect:/biz/biz_mypage_room";
	}
	
	// 객실 삭제 처리
	@PostMapping("/biz/biz_room_delete")
	public String deleteRoomPhoto(HttpSession session,
			@RequestParam("roomNo") int roomNo,
			HttpServletRequest request) {
		try {
	        // DB에서 삭제
			// 숙소 번호
			// 로그인한 비즈니스 회원의 숙소정보 가져오기
		    AccoVO acco = (AccoVO) session.getAttribute("acco");
			System.out.println(roomNo);
			int accoNo = acco.getAccoNo();
			
	        List<AccoPhotoVO> photoList = bizService.getPhotosByBizIdAndRoomNo(accoNo, roomNo);

	        // 실제 파일도 삭제
	        String uploadDir = request.getSession().getServletContext().getRealPath("/resources/img/room");
	        
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
	}
