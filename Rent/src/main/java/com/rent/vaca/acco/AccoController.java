package com.rent.vaca.acco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/acco")
public class AccoController {
	private static final Logger logger = LoggerFactory.getLogger(AccoController.class);
	
	private final AccoService accoService;

	@Autowired
	public AccoController(AccoService accoService) {
		this.accoService = accoService;
	}
	
	//���� 1�� ��ȸ
	@RequestMapping(value="/view/{accoNo}", method=RequestMethod.GET)
	public String view(@PathVariable("accoNo") int accoNo, Model model) {
		AccoVO accoVO = accoService.selectAccoByAccoNo(accoNo);
		model.addAttribute("acco", accoVO);
		return "accomo/acco_view";
	}
}
