package com.ct.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ct.model.Login;

@Component
public interface IService {
	
	public ModelAndView getAllTvs();
	public boolean isValid(Login log);
	
}
