package com.rent.vaca.search;

import java.util.List;

import com.rent.vaca.acco.AccoVO;

public interface SearchService {
	List<AccoVO> search(SearchVO vo);
}
