package com.ct.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ct.model.Login;
import com.ct.model.Tv;
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
	public ModelAndView authenticate(@Valid @ModelAttribute("login") Login log, BindingResult br, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		if (br.hasErrors()) {
			mv.setViewName("loginPage");
			return mv;
		} else {
			Login userLog = service.isValid(log);
			System.out.println(userLog);
			if (userLog.getUsername() == null && userLog.getRole() == null){
				mv.setViewName("loginPage");
				mv.addObject("errorMessage", "Given Username Does not exist");
				return mv;
			}else {
				HttpSession session = req.getSession();
				session.setAttribute("username", log.getUsername());
				session.setAttribute("role", userLog.getRole());
				System.out.println(userLog.getRole());
				if (userLog.getRole().equals("admin")) {
					mv.setViewName("redirect:/admin");
					return mv;
				} else {
					mv = homePage();
					mv.setViewName("redirect:/home");
					return mv;
				}
			}
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
	
	@RequestMapping("/admin")
	public String viewAdmin(HttpServletRequest req) {
		
		if ((req.getSession(false).getAttribute("role").equals("admin"))) {
			return "admin";
		} else {
			return "redirect:/home";
		}
		
	}
	
	@RequestMapping("newUser_Registration")
	public ModelAndView newUserRegistration() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("registration");
		return mv;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute User user, BindingResult br, Login log, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors()) {
			System.out.println(br);
			mv.setViewName("registration");
		}
		
		else {
			log.setUsername(user.getUsername());
			log.setPassword(user.getPassword());
			log.setRole("user");
			boolean isAdded = service.registerUser(user, log);
			System.out.println(user + " " + isAdded);

			if (isAdded) {
				mv.setViewName("loginPage");
				req.setAttribute("message", "successfully registered");
				
			} else {
				System.out.println(isAdded);
				mv.setViewName("loginPage");
				
				req.setAttribute("message", "given username already exists");
			}
		}
		return mv;
	}
	
	
	
	/* Admin Login Options  */
	
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView();
		if(req.getSession(false).getAttribute("role").equals("admin")) {
		Tv t = new Tv();
		mv.addObject("tv", t);
		mv.setViewName("add");
		return mv;
		}
		else {
			mv.setViewName("redirect:/home");
			return mv;
		}
	}
	
	@RequestMapping(value="/addtv", method=RequestMethod.POST)
	public String addTv(@Valid @ModelAttribute("tv") Tv t, BindingResult br, HttpServletRequest req, @RequestParam CommonsMultipartFile file,  
	           HttpSession session) throws IOException {
		
		if (req.getSession(false).getAttribute("role").equals("admin")) {

			if (br.hasErrors()) {
				return "add";
			}

			else {

				final String UPLOAD_DIRECTORY = "/images";
				ServletContext context = session.getServletContext();
				String path = context.getContextPath();
				String filename = file.getOriginalFilename();

				t.setTvPath(path + "/resources/images/" + filename);
				boolean isAdded = service.addTv(t);
				if (isAdded) {
					req.setAttribute("tvAddStatusMessage", "tv successfully added into database");
				} else {
					req.setAttribute("tvAddStatusMessage", "tv is not added to database, error has occured");
				}

				/* Image upload */

				System.out.println(
						"C:/Users/Trainingvdi/Downloads/TvShop-master/TvShop-master/src/main/webapp/resources/images"
								+ filename);

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(
						"C:/Users/Trainingvdi/Downloads/TvShop-master/TvShop-master/src/main/webapp/resources/images/"
								+ filename)));
				stream.write(bytes);
				stream.flush();
				stream.close();

				return "add";
			}
		} else {
			return "redirect:/home";
		}
	}
	
	@RequestMapping("/displayTvAdmin")
	public ModelAndView displayAdmin(HttpSession session, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		
		if(req.getSession(false).getAttribute("role").equals("admin")) {
		mv = service.getAllTvs();
		System.out.println(session.getAttribute("role"));
		mv.setViewName("home");
		return mv;
		}
		else {
			mv.setViewName("redirect:/home");
			return mv;
		}
	}
	
	@RequestMapping("/delete")
	public String deleteTv(@RequestParam("id") int id, HttpServletRequest req) {
		
		if(req.getSession(false).getAttribute("role").equals("admin")) {
			boolean isDeleted = service.deleteTv(id);
			return "redirect:/home";
		}
		else {
		return "redirect:/home";
		}
	}
	
	@RequestMapping("/modify")
	public ModelAndView modifyTv(ModelAndView mv,HttpServletRequest req ,@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("brand") String brand, @RequestParam("price") int price, @RequestParam("des") String des) {
		Tv t = new Tv();
		if(req.getSession(false).getAttribute("role").equals("admin")) {
			t.setTvId(id);
			t.setTvName(name);
			t.setTvBrand(brand);
			t.setTvPrice(price);
			t.setTvDescription(des);
			mv.addObject("modifytv", t);
			mv.setViewName("modify");
			return mv;
		}
		else {
			mv.setViewName("redirect:/home");
			return mv;
		}
		
	}
	
	@RequestMapping( value = "/modifytv", method = RequestMethod.POST)
	public String updateTv(@ModelAttribute("modifytv") Tv t, BindingResult br, HttpServletRequest req) {
		if(req.getSession(false).getAttribute("role").equals("admin")) {
			System.out.println(t.getTvName());
			boolean isUpdated = service.update(t);
			System.out.println(t.getTvName());
		}
		return "modify";
	}
}

