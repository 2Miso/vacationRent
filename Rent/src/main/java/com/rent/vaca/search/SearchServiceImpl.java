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
		if(vo.getOrderBy()==null) {
			vo.setOrderBy("popular");
		}
		switch(vo.getOrderBy()) {
			case "highest":
				vo.setOrderQuery("price desc");
				break;
			case "lowest":
				vo.setOrderQuery("price asc");
				break;
			default:
				vo.setOrderQuery("starAvg desc");
		}
		
		return accoRepository.search(vo);
	}

}
