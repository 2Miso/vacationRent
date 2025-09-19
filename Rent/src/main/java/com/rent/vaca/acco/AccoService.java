package com.rent.vaca.acco;

import java.util.List;

import com.rent.vaca.review.ReviewVO;
import com.rent.vaca.user.InterestVO;

public interface AccoService {
	//��� ���� 5��
	List<AccoPhotoVO> selectTopPhotos(int accoNo);
	
	//����, �����, ���Ǹ�ϰ� ���ǻ���1��
	AccoVO selectAccoByAccoNo(int accoNo);
	
	//���� ��� ��ȸ
	List<ReviewVO> selectReviewsByAccoNo(AccoVO acco);
	
	//���ɼ��� ��ư ������ ��
	int hitInterestBtn(InterestVO vo);
	
	//���ɼ��� ��ȸ
	boolean selectInterestOne(InterestVO vo);
	
	//���䰳�� ��ȸ
	int countReview(int accoNo);
	
	//���� ��� ��ȸ
	Double starAvg(int accoNo);
	
	//������� ������ ��ü
	List<AccoPhotoVO> photoModal(AccoPhotoVO vo);
}
