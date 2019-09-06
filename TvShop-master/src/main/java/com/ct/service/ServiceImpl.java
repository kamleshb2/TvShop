package com.ct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ct.dao.ITvDao;
import com.ct.model.Login;
import com.ct.model.Tv;
import com.ct.model.User;

@Service
public class ServiceImpl implements IService {

	@Autowired
	ITvDao tvDao;
	
	public ModelAndView getAllTvs() {
		
		ModelAndView mv = new ModelAndView();
		mv = tvDao.getAllTvs();
		return mv;
		
	}

	public Login isValid(Login log) {
		Login userLog=tvDao.isValid(log);
		return userLog;
	}

	public boolean registerUser(User user, Login log) {
		boolean isAdded = tvDao.registerUser(user, log);
		return isAdded;
	}

	public boolean addTv(Tv t) {
		
		boolean isAdded = tvDao.addTv(t);
		return isAdded;
	}

	public boolean deleteTv(int id) {
		boolean isDeleted = tvDao.deletTv(id);
		return isDeleted;
	}

	public boolean update(Tv t) {
		boolean isUpdated = tvDao.update(t);
		return false;
	}
}
