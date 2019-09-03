package com.ct.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ct.model.Login;
import com.ct.model.Tv;

@Component
public class TvDaoImpl implements ITvDao {

	
	Session session = DbUtil.getSessionFactory().openSession();
	
	public ModelAndView getAllTvs() {
		
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Tv.class);
		List<Tv> tv = criteria.list(); 
		ModelAndView mv = new ModelAndView();
		mv.addObject("tvList", tv);
		session.getTransaction().commit();
		System.out.println(tv);
		return mv;
	}

	public boolean isValid(Login log) {
	        boolean isValidUser = false;
	        try {
	        	Criteria c=session.createCriteria(Login.class);  
	        	c.add(Restrictions.eq("username", log.getUsername()));
	        	c.add(Restrictions.eq("password", log.getPassword()));
	        	List<Login> list = c.list();
	        	System.out.println(list + log.getUsername());
	            if(list.get(0).getUsername() != null && list.size() > 0) {
	                isValidUser = true;
	                System.out.println(list.get(0).getUsername());
	                System.out.println(list.get(0).getRole());
	            }
	        } catch(Exception e) {
	            isValidUser = false;  
	        }
	        return isValidUser;
	}

	
}
