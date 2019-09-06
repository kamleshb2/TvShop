package com.ct.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ct.model.Login;
import com.ct.model.Tv;
import com.ct.model.User;

@Component
public interface ITvDao {

	public ModelAndView getAllTvs();
	public Login isValid(Login log);
	public boolean registerUser(User user, Login log);
	public boolean addTv(Tv t);
	public boolean deletTv(int id);
	public boolean update(Tv t);
}
