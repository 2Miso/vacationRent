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
	
	//���� 1�� ��ȸ
	@RequestMapping(value="/acco/view/{accoNo}", method=RequestMethod.GET)
	public String view(@PathVariable("accoNo") int accoNo, AccoVO acco, Model model) {
		//��� ���� 5��
		List<AccoPhotoVO> topPhotos = accoService.selectTopPhotos(accoNo);
		model.addAttribute("topPhotos", topPhotos);
		
		//����, �����, ���Ǹ�ϰ� ���ǻ���1��
		AccoVO accoVO = accoService.selectAccoByAccoNo(accoNo);
		model.addAttribute("acco", accoVO);

		//���䰳��
		int reviewCount = accoService.countReview(accoNo);
		model.addAttribute("reviewCount", reviewCount);

		//�������
		Double starAvg = accoService.starAvg(accoNo);
		if(starAvg != null) {
			starAvg = Math.round(accoService.starAvg(accoNo)*10)/10.0;
		}
		model.addAttribute("starAvg", starAvg);
		
		//������
		acco.setAccoNo(accoNo);
		acco.setOrderBy("newest");
		List<ReviewVO> reviewList = accoService.selectReviewsByAccoNo(acco);
		model.addAttribute("reviewList", reviewList);
		
		return "accomo/acco_view";
	}
	
	//���� ���ı��� ����
	@GetMapping("/accomo/reviewList/{accoNo}")
	public String reviewList(@PathVariable("accoNo") int accoNo, @RequestParam("orderBy") String orderBy, AccoVO acco, Model model) {
		acco.setAccoNo(accoNo);
		acco.setOrderBy(orderBy);
		List<ReviewVO> reviewList = accoService.selectReviewsByAccoNo(acco);
		model.addAttribute("reviewList", reviewList);
		
		//���䰳��
		int reviewCount = accoService.countReview(accoNo);
		model.addAttribute("reviewCount", reviewCount);
		
		return "accomo/reviewList";
	}
	
	
	//���ɼ��� ��Ͽ��� ��ȸ
	@GetMapping("/mypage/interest")
	@ResponseBody
	public boolean selectInterestOne(@RequestParam("userId") int userId, @RequestParam("accoNo") int accoNo, InterestVO vo) {
		vo.setUserId(userId);
		vo.setAccoNo(accoNo);
		return accoService.selectInterestOne(vo);
	}
	
	//���ɼ��� ��ư Ŭ��
	@PostMapping("/mypage/interest")
	@ResponseBody
	public int hitInterestBtn(@RequestParam("userId") int userId, @RequestParam("accoNo") int accoNo, InterestVO vo) {
		vo.setUserId(userId);
		vo.setAccoNo(accoNo);
		return accoService.hitInterestBtn(vo);
	}
	
	//������� ������ ��ü
	@GetMapping("/acco/photoModal")
	@ResponseBody
	public List<AccoPhotoVO> photoModal(@RequestParam("accoNo") int accoNo, @RequestParam("roomName") String roomName, AccoPhotoVO vo) {
		vo.setAccoNo(accoNo);
		vo.setRoomName(roomName);
		return accoService.photoModal(vo);
	}
}