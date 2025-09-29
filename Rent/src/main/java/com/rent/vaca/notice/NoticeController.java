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
	
	//임시 : 헤더 햄버거 아이콘 드롭다운 회원가입 클릭 시 이동할 페이지
	@RequestMapping(value="join")
	public String joinPage() {
		return "/join/main";
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
	
	//관리자 공지 작성
	@RequestMapping(value="/notice/write", method=RequestMethod.GET)
	public String write() {
		return "notice/n_write";
	}
	@RequestMapping(value="/notice/write", method=RequestMethod.POST)
	public String write(@ModelAttribute NoticeVO vo, @SessionAttribute("user") UserVO user) {
		vo.setUserId(user.getId());
		vo.setType("N");
		noticeService.insertNoticeOne(vo, user.getGrade());
		
		return "redirect:/notice/view/" + vo.getNoticeNo();
	}
	
	//관리자 1대1 문의 답변 확인
	@RequestMapping(value="/admin/QnA/answer/{noticeNo}", method=RequestMethod.GET)
	public String answerResult(@PathVariable("noticeNo") int noticeNo, Model model) {
		NoticeVO vo = noticeService.selectQuestionByNoticeNo(noticeNo);
		model.addAttribute("question", vo);
		return "admin/qna_result";
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
		return "redirect:/mypage/question/" + vo.getNoticeNo(); 
	}
	
	//개인회원 1대1 문의 단건조회
  @RequestMapping(value="/mypage/question/{noticeNo}", method=RequestMethod.GET)
  public String question(@PathVariable("noticeNo") int noticeNo, Model model) {
	  NoticeVO vo = noticeService.selectQuestionByNoticeNo(noticeNo);
	  model.addAttribute("question", vo);
	  return "/notice/question_view";
  }
 //개인회원 1대1 문의 삭제
  @RequestMapping(value="/mypage/question/{noticeNo}", method=RequestMethod.POST)
  public String deleteQuestion(@PathVariable("noticeNo") int noticeNo, @SessionAttribute("user") UserVO user) {
	  int result = noticeService.deleteQuestionOne(noticeNo, user.getId());
	  if(result <= 0) {
		  return "redirect:/customer/question/" + noticeNo;
	  }
	  return "redirect:/mypage/myQnA";
  }
  
	//관리자 1대1 문의 답변 작성
	@RequestMapping(value="/admin/QnA/{noticeNo}", method=RequestMethod.GET)
	public String answer(@PathVariable("noticeNo") int noticeNo, Model model) {
		NoticeVO vo = noticeService.selectQuestionByNoticeNo(noticeNo);
		model.addAttribute("question", vo);
		return "admin/answer_to_question";
	}
	@RequestMapping(value="/admin/QnA/{noticeNo}", method=RequestMethod.POST)
	public String answer(@PathVariable("noticeNo") int noticeNo, @ModelAttribute NoticeVO vo, @SessionAttribute("user") UserVO user) {
		vo.setAnsweryn("Y");
		int result = noticeService.updateAnswerOne(vo, user);
		if(!(result==1)) {
			return "redirect:/admin/QnA/" + noticeNo;
		}
		return "redirect:/admin/QnA/answer/" + noticeNo;
	}
	
	//관리자 1대1 문의 목록 조회
	@RequestMapping(value="/mypage/myQnA", method=RequestMethod.GET)
	public String myQna(@SessionAttribute("user") UserVO user, Model model) {
		return "/user/my_question";
	}
}