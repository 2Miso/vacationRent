package com.rent.vaca.notice;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import com.rent.vaca.user.UserVO;

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

	//1대1 문의 작성
	@RequestMapping(value="/customer/question", method=RequestMethod.GET)
	public String question() {
		return "notice/q_write";
	}
	@RequestMapping(value="/customer/question", method=RequestMethod.POST)
	public String question(
							@ModelAttribute NoticeVO vo,
							@RequestParam("attachment") List<MultipartFile> attach,
							@SessionAttribute("user") UserVO user
							) throws IllegalArgumentException, IOException {
		vo.setUserId(user.getId());
		vo.setType("Q");
		noticeService.insertQuestionOne(vo, attach);
		return "redirect:/customer/question/" + vo.getNoticeNo(); 
	}

	//1대1 문의 단건조회
  @RequestMapping(value="/customer/question/{noticeNo}", method=RequestMethod.GET)
  public String question(@PathVariable("noticeNo") int noticeNo, Model model) {
	  NoticeVO vo = noticeService.selectQuestionByNoticeNo(noticeNo);
	  model.addAttribute("notice", vo);
	  return "/notice/question_view";
  }
 
}