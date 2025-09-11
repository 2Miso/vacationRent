package com.rent.vaca.notice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private final NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	//공지 목록 조회
	@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<NoticeVO> list = noticeService.selectAllNotice();
		
		model.addAttribute("noticeList", list);
		
		return "notice/notice_list";
	}
	
	//공지 단건조회
	@RequestMapping(value="/notice/view/{noticeNo}", method=RequestMethod.GET)
	public String view(@PathVariable("noticeNo") int noticeNo, Model model) {
		NoticeVO vo = noticeService.selectNoticeByNoticeNo(noticeNo);
		model.addAttribute("notice", vo);
		return "notice/notice_view";
	}
	
	//공지 작성
	@RequestMapping(value="/notice/write", method=RequestMethod.GET)
	public String write() {
		return "notice/n_write";
	}
	@RequestMapping(value="/notice/write", method=RequestMethod.POST)
	public String write(@ModelAttribute NoticeVO vo) {
		return null;
	}
	
	//FAQ 목록 조회
	@RequestMapping(value="/customer/faq", method=RequestMethod.GET)
	public String faq() {
		return "notice/customer";
	}
}
