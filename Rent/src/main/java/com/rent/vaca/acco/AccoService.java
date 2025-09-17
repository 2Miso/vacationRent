package com.rent.vaca.acco;

import com.rent.vaca.user.InterestVO;

public interface AccoService {
	//���� 1�� ��ȸ
	AccoVO selectAccoByAccoNo(int accoNo);
	
	//���ɼ��� ��ư ������ ��
	int hitInterestBtn(InterestVO vo);
	
	//���ɼ��� ��ȸ
	boolean selectInterestOne(InterestVO vo);
	
	//���䰳�� ��ȸ
	int countReview(int accoNo);
	
	//���� ��� ��ȸ
	Double starAvg(int accoNo);
}
