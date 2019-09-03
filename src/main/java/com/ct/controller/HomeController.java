package com.ct.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ct.model.Login;
import com.ct.model.User;
import com.ct.service.IService;
import com.mysql.cj.protocol.ServerSession;

@Controller
public class HomeController {

	@Autowired
	IService service;
	
	
	
	@RequestMapping("/")
	public ModelAndView homePage() {
		
		
		ModelAndView mv = new ModelAndView();
		mv = service.getAllTvs();
		mv.setViewName("home");
		System.out.println("in home" );
		return mv;
	}
	
/*	@RequestMapping("tvinfo")
	public String tvInfo(HttpServletResponse res , @RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("brand") String brand, @RequestParam("price") int price, @RequestParam("des") String des ) {
		
		System.out.println(des);
		
		return null;
	}*/
	
	@RequestMapping("/login")
	public String loginPage() {
		
		return "loginPage";
	}
	
	@RequestMapping(value = "/authenticateUser", method=RequestMethod.POST)
	public ModelAndView authenticate(@Valid  @ModelAttribute("login") Login log, HttpServletRequest req, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		boolean isValidUser = service.isValid(log); 
		System.out.println(isValidUser);
		if(isValidUser) {
			HttpSession session = req.getSession();
			session.setAttribute("username", log.getUsername());
			session.setAttribute("role", log.getRole());
			mv = homePage();
			mv.setViewName("redirect:/home");
			return mv;
		}
		if(br.hasErrors()) {
			System.out.println(br); 
			mv.setViewName("loginPage");
			return mv;
		}
		else {
			mv.setViewName("loginPage");
			mv.addObject("errorMessage","Given Username Does not exist" );
			return mv;
		}
		
	
	}
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		req.getSession().invalidate();
		mv = homePage();
		mv.setViewName("redirect:/home");
		return mv;
	}
	
	@RequestMapping("/home")
	public ModelAndView viewHomePage() {
		ModelAndView mv = new ModelAndView();
		mv = service.getAllTvs();
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("newUser_Registration")
	public ModelAndView newUserRegistration() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("registration");
		return mv;
	}

	
}

