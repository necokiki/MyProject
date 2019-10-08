package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.SweetDTO;
import com.dto.WorkDTO;

@Repository
public class WorkDAO {

	@Autowired
	SqlSessionTemplate template;

	public List<WorkDTO> workList(String wCategory) {
		List<WorkDTO> list = template.selectList("WorkMapper.workList", wCategory);
		return list;

	}

	public WorkDTO workDetail(String wCode) {
		WorkDTO dto = template.selectOne("WorkMapper.workDetail", wCode);
		return dto;
	}

	public void sweetAdd(SweetDTO sweet) {
		template.insert("SweetMapper.sweetAdd", sweet);

	}

	public List<SweetDTO> sweetList(String userid) {
		List<SweetDTO> list = template.selectList("SweetMapper.sweetList", userid);
		return list;
	}

	public void sweetDel(String num) {
		int n = template.delete("SweetMapper.sweetDel", num);

	}

	public void workUp(WorkDTO wDTO) {
		int n = template.insert("WorkMapper.workUp",wDTO);
		
	}

	public void delAllSweet(ArrayList<String> list) {
		
		int n = template.delete("SweetMapper.delAllSweet",list);
		
	}


	public WorkDTO cartByNum(String wCode) {
		WorkDTO wDTO = template.selectOne("WorkMapper.cartByNum" , wCode);
		return wDTO;
	}
	
	public List<WorkDTO> myWorkList(String artistname) {
		List<WorkDTO> list = template.selectList("WorkMapper.myWorkList",artistname);
		return list;
	}

	public void workDel(String wCode) {
		int n = template.delete("WorkMapper.workDel",wCode);
	}

	public void sweetAllDel(String userid) {
		// TODO Auto-generated method stub
		int n = template.delete("SweetMapper.sweetAllDel",userid );
	}

	public List<SweetDTO> sweetCheck(String userid) {
		List<SweetDTO> list = template.selectList("SweetMapper.sweetCheck", userid);
		return list;
	}

	public List<String> sweetChartSource() {
		List<String> list = template.selectList("SweetMapper.sweetChartSource");
		return list;
	}

}
