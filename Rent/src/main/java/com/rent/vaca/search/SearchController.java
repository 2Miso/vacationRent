package com.rent.vaca.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rent.vaca.acco.AccoVO;

@Controller
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	private final SearchService searchService;
	
	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	//메인페이지
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "main/main";
	}
	
	//검색결과 목록
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String list(SearchVO vo, Model model) {
		logger.info(vo.toString());
		List<AccoVO> accoList = searchService.search(vo);
		model.addAttribute("accoList", accoList);
		model.addAttribute("search", vo);
		return "main/list";
	}
}
