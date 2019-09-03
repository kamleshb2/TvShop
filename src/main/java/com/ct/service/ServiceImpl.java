package com.ct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ct.dao.ITvDao;
import com.ct.model.Login;

@Service
public class ServiceImpl implements IService {

	@Autowired
	ITvDao tvDao;
	
	public ModelAndView getAllTvs() {
		
		ModelAndView mv = new ModelAndView();
		mv = tvDao.getAllTvs();
		return mv;
		
	}

	public boolean isValid(Login log) {
		boolean isValidUser=tvDao.isValid(log);
		return isValidUser;
	}
}
