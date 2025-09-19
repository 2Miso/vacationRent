package com.rent.vaca.acco;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.rent.vaca.review.ReviewVO;
import com.rent.vaca.user.InterestVO;
import com.rent.vaca.user.UserVO;

@Controller
public class AccoController {
	private static final Logger logger = LoggerFactory.getLogger(AccoController.class);
	
	private final AccoService accoService;

	@Autowired
	public AccoController(AccoService accoService) {
		this.accoService = accoService;
	}
	
	//숙소 1건 조회
	@RequestMapping(value="/acco/view/{accoNo}", method=RequestMethod.GET)
	public String view(@PathVariable("accoNo") int accoNo, AccoVO acco, Model model) {
		//상단 사진 5개
		List<AccoPhotoVO> topPhotos = accoService.selectTopPhotos(accoNo);
		model.addAttribute("topPhotos", topPhotos);
		
		//숙소, 사업자, 객실목록과 객실사진1개
		AccoVO accoVO = accoService.selectAccoByAccoNo(accoNo);
		model.addAttribute("acco", accoVO);

		//리뷰개수
		int reviewCount = accoService.countReview(accoNo);
		model.addAttribute("reviewCount", reviewCount);

		//별점평균
		Double starAvg = accoService.starAvg(accoNo);
		if(starAvg != null) {
			starAvg = Math.round(accoService.starAvg(accoNo)*10)/10.0;
		}
		model.addAttribute("starAvg", starAvg);
		
		//리뷰목록
		acco.setAccoNo(accoNo);
		acco.setOrderBy("newest");
		List<ReviewVO> reviewList = accoService.selectReviewsByAccoNo(acco);
		model.addAttribute("reviewList", reviewList);
		
		return "accomo/acco_view";
	}
	
	//리뷰 정렬기준 변경
	@GetMapping("/accomo/reviewList/{accoNo}")
	public String reviewList(@PathVariable("accoNo") int accoNo, @RequestParam("orderBy") String orderBy, AccoVO acco, Model model) {
		acco.setAccoNo(accoNo);
		acco.setOrderBy(orderBy);
		List<ReviewVO> reviewList = accoService.selectReviewsByAccoNo(acco);
		model.addAttribute("reviewList", reviewList);
		
		//리뷰개수
		int reviewCount = accoService.countReview(accoNo);
		model.addAttribute("reviewCount", reviewCount);
		
		return "accomo/reviewList";
	}
	
	
	//관심숙소 등록여부 조회
	@GetMapping("/mypage/interest")
	@ResponseBody
	public boolean selectInterestOne(@RequestParam("userId") int userId, @RequestParam("accoNo") int accoNo, InterestVO vo) {
		vo.setUserId(userId);
		vo.setAccoNo(accoNo);
		return accoService.selectInterestOne(vo);
	}
	
	//관심숙소 버튼 클릭
	@PostMapping("/mypage/interest")
	@ResponseBody
	public int hitInterestBtn(@RequestParam("userId") int userId, @RequestParam("accoNo") int accoNo, InterestVO vo) {
		vo.setUserId(userId);
		vo.setAccoNo(accoNo);
		return accoService.hitInterestBtn(vo);
	}
	
	//사진모달 데이터 교체
	@GetMapping("/acco/photoModal")
	@ResponseBody
	public List<AccoPhotoVO> photoModal(@RequestParam("accoNo") int accoNo, @RequestParam("roomName") String roomName, AccoPhotoVO vo) {
		vo.setAccoNo(accoNo);
		vo.setRoomName(roomName);
		return accoService.photoModal(vo);
	}
}