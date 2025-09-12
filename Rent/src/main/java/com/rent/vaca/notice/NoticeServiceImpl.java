package com.rent.vaca.notice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticeServiceImpl implements NoticeService{
	private final NoticeRepository noticeRepository;
	private final ServletContext context;
	private final QuestionAttachRepository attachRepository;
	
	@Autowired
	public NoticeServiceImpl(NoticeRepository noticeRepository, ServletContext context,
			QuestionAttachRepository attachRepository) {
		this.noticeRepository = noticeRepository;
		this.context = context;
		this.attachRepository = attachRepository;
	}
	
	@Override
	public List<NoticeVO> selectAllNotice() {
		return noticeRepository.selectAllNotice();
	}


	@Override
	public NoticeVO selectNoticeByNoticeNo(int noticeNo) {
		return noticeRepository.selectNoticeByNoticeNo(noticeNo);
	}

	//1대1문의 작성
	@Override
	@Transactional //문의글 등록 실패하면 첨부파일도 등록되지 않음 
	public void insertQuestionOne(NoticeVO vo, List<MultipartFile> attach) throws IllegalStateException, IOException {
		//문의글 insert
		noticeRepository.insertQuestionOne(vo);

		//첨부파일 업로드 폴더 준비 : 경로 webapp/questions/파일이름.확장자
		String path = context.getRealPath("/questions/");
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		List<QuestionAttachVO> list = new ArrayList<>();
		for(MultipartFile a:attach) {
			if(a.isEmpty()) {
				continue;
			}
			
			//파일 업로드
			String originalName = a.getOriginalFilename();
			
			String ext = originalName.substring(originalName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString() + ext;
			
			a.transferTo(new File(path + savedName));
			
			QuestionAttachVO attachVO = new QuestionAttachVO();
			attachVO.setNoticeNo(vo.getNoticeNo());
			attachVO.setOriginalName(originalName);
			attachVO.setSavedName(savedName);
			
			list.add(attachVO);
		}
		
		if(!list.isEmpty()) {
			attachRepository.insertFiles(list);
		}
		
	}

	@Override
	public NoticeVO selectQuestionByNoticeNo(int noticeNo) {
		return noticeRepository.selectQuestionByNoticeNo(noticeNo);
	}
	
}
