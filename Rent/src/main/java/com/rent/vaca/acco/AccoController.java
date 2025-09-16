package com.rent.vaca.acco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.rent.vaca.user.InterestVO;
import com.rent.vaca.user.UserVO;

@Controller
@RequestMapping("/acco")
public class AccoController {
	private static final Logger logger = LoggerFactory.getLogger(AccoController.class);
	
	private final AccoService accoService;

	@Autowired
	public AccoController(AccoService accoService) {
		this.accoService = accoService;
	}
	
	//숙소 1건 조회
	@RequestMapping(value="/view/{accoNo}", method=RequestMethod.GET)
	public String view(@PathVariable("accoNo") int accoNo, Model model) {
		AccoVO accoVO = accoService.selectAccoByAccoNo(accoNo);
		model.addAttribute("acco", accoVO);
		return "accomo/acco_view";
	}
	
	//관심숙소 버튼 클릭
	@PostMapping("/mypage/interest")
	@ResponseBody
	public int hitInterestBtn(@RequestParam("userId") int userId, @RequestParam("accoNo") int accoNo, InterestVO vo) {
		vo.setUserId(userId);
		vo.setAccoNo(accoNo);
		return accoService.hitInterestBtn(vo);
	}
}
