package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.WorkDAO;
import com.dto.MemberADTO;
import com.dto.SweetDTO;
import com.dto.WorkDTO;

@Service
public class WorkService {
	
	@Autowired
	WorkDAO dao;

	public List<WorkDTO> workList(String wCategory) {
	
		List<WorkDTO> list = dao.workList(wCategory);
		return list;
	}


	public void workUp(WorkDTO wDTO) {
		dao.workUp(wDTO);	
	}

	public WorkDTO workDetail(String wCode) {
		
		WorkDTO dto = dao.workDetail(wCode);
		return dto;
	}

	public void sweetAdd(SweetDTO sweet) {
		
		dao.sweetAdd(sweet);
		
	}

	public List<SweetDTO> sweetList(String userid) {
	
		List<SweetDTO> dto = dao.sweetList(userid);
		return dto;
	}

	public void sweetDel(String num) {
		dao.sweetDel(num);
	}


	public void delAllSweet(ArrayList<String> list) {
		dao.delAllSweet(list);
		
	}


	public WorkDTO cartByNum(String wCode) {
		
		WorkDTO wDTO = dao.cartByNum(wCode);
		return wDTO;
	}

	public List<WorkDTO> myWorkList(String artistname) {
		List<WorkDTO> list = dao.myWorkList(artistname);
		return list;
	}


	public void workDel(String wCode) {
		dao.workDel(wCode);
		
	}


	public void sweetAllDel(String userid) {
		// TODO Auto-generated method stub
		dao.sweetAllDel(userid);
	}


	public List<SweetDTO> sweetCheck(String userid) {
		List<SweetDTO> dto = dao.sweetCheck(userid);
		return dto;
	}


	public List<String> sweetChartSource() {
		List<String> list = dao.sweetChartSource();
		return list;
	}





}
