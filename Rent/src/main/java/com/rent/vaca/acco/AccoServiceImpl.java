package com.rent.vaca.acco;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.vaca.room.RoomRepository;

@Service
public class AccoServiceImpl implements AccoService {
	private final AccoRepository accoRepository;
	private final RoomRepository roomRepository;
	
	@Autowired
	public AccoServiceImpl(AccoRepository accoRepository, RoomRepository roomRepository) {
		this.accoRepository = accoRepository;
		this.roomRepository = roomRepository;
	}
	
	//¼÷¼Ò 1°Ç Á¶È¸
	public AccoVO selectAccoByAccoNo(int accoNo) {
		return accoRepository.selectAccoByAccoNo(accoNo);
	}
}
