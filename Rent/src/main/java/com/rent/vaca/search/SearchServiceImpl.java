package com.rent.vaca.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.vaca.acco.AccoRepository;
import com.rent.vaca.acco.AccoVO;

@Service
public class SearchServiceImpl implements SearchService{

	private final AccoRepository accoRepository;
	
	@Autowired
	public SearchServiceImpl(AccoRepository accoRepository) {
		this.accoRepository = accoRepository;
	}

	@Override
	public List<AccoVO> search(SearchVO vo) {
		return accoRepository.search(vo);
	}

}
