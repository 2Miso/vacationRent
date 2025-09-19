package com.rent.vaca.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	//메인페이지
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "main/main";
	}
	
	//검색결과 목록
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String list() {
		return "main/list";
	}
}
