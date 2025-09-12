package com.rent.vaca.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionAttachRepository {
	private final SqlSession template;

	@Autowired
	public QuestionAttachRepository(SqlSession template) {
		this.template = template;
	}
	
	//∆ƒ¿œ√∑∫Œ
	public int insertFiles(List<QuestionAttachVO> list) {
		return template.insert("questionAttachMapper.insertFiles", list);
	}
}
