package com.ct.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ct.model.Login;
import com.ct.model.Tv;
import com.ct.model.User;

@Component
public class TvDaoImpl implements ITvDao {

	
	
	
	public ModelAndView getAllTvs() {
		
		Session session = DbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Tv.class);
		List<Tv> tv = criteria.list(); 
		ModelAndView mv = new ModelAndView();
		mv.addObject("tvList", tv);
		session.getTransaction().commit();
		session.close();
		System.out.println(tv);
		return mv;
	}

	public Login isValid(Login log) {
		Session session = DbUtil.getSessionFactory().openSession();
		Login userLog = new Login();
	        try {
	        	Criteria c=session.createCriteria(Login.class);  
	        	c.add(Restrictions.eq("username", log.getUsername()));
	        	c.add(Restrictions.eq("password", log.getPassword()));
	        	List<Login> list = c.list();
	        	System.out.println(list + log.getUsername());
	            if(list.get(0).getUsername() != null && list.get(0).getPassword() != null) {
	                userLog.setRole(list.get(0).getRole());
	                userLog.setUsername(list.get(0).getUsername());
	                session.close();
	              
	            }
	        }
	            catch(Exception e) {
	            	
	            }
	        
	        return userLog;
	        
	}

	public boolean registerUser(User user, Login log) {
		Session session = DbUtil.getSessionFactory().openSession();
		
		boolean isAlreadyExists = searchUsername(log);
		
		if(isAlreadyExists) {
			return false;
		}
		else {
		session.beginTransaction();
		session.save(user);
		session.save(log);
		session.getTransaction().commit();
		session.close();
		return true;
		}
	}

	public boolean searchUsername(Login log) {
		
		Session session = DbUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		Criteria c = session.createCriteria(Login.class);
		c.add(Restrictions.eq("username", log.getUsername()));
		List<Login> list = c.list();
		session.getTransaction().commit();
		
		if(list.isEmpty()) {
			return false;
		}
		
		else { 
		return true;
		}
	}

	
	public boolean addTv(Tv t) {
		boolean isUnique = searchTv(t);
		Session session = DbUtil.getSessionFactory().openSession();
		
		if(isUnique) {
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		else {
		return false;
		}
	}

	
	public boolean searchTv(Tv t) {
		Session session = DbUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		Criteria c = session.createCriteria(Tv.class);
		c.add(Restrictions.eq("tvName", t.getTvName()));
		List<Tv> list = c.list();
		session.getTransaction().commit();
		
		if(list.isEmpty()){
			return true;
		}
		else {
		return false;
		}
	}

	public boolean deletTv(int id) {
		Session session = DbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "delete from Tv where tv_id= :tvId";
		session.createQuery(hql).setInteger("tvId", id).executeUpdate();
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public boolean update(Tv t) {
		Session session = DbUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Tv tv = (Tv)session.get(Tv.class, t.getTvId());
		System.out.println(tv);
		tv.setTvName(t.getTvName());
		tv.setTvBrand(t.getTvBrand());
		tv.setTvPrice(t.getTvPrice());
		tv.setTvDescription(t.getTvDescription());
		session.update(tv);
		session.getTransaction().commit();
		session.close();
		return false;
	}
	

	
}
