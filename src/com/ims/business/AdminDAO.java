package com.ims.business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Administration;
import com.ims.data.User;

public class AdminDAO {
	
	private SessionFactory sessionFactory;

	public void changeAdminProfile(String oldUserName,User user)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		User olduser = (User)session.get(User.class, oldUserName);
		session.delete(olduser);
		session.getTransaction().commit();
		session.close();
		
		Session session2 = getSessionFactory().openSession();
		session2.beginTransaction();
		session2.save(user);
		session2.beginTransaction().commit();
		session2.close();
		
	}
	
	
	public User getAdminProfile(String userName)
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from User as com  where com.userName='" + userName + "'";
		Query query = session.createQuery(SQL_QUERY);
		List<User> list = ((org.hibernate.Query) query).list();
		session.close();

		if(list.size()>0)
			return list.get(0);
		else 
			return null;
		
	}
	
	
	public void updateAdministrationTable(Administration administration)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Administration oldAdministration= (Administration)session.get(Administration.class, 1234);
		
		if(oldAdministration==null)
		{
			session.close();
			Session session2 = getSessionFactory().openSession();
			session2.beginTransaction();
			session2.save(administration);
			session2.beginTransaction().commit();
			session2.close();
			
		}
		else
		{
			session.delete(oldAdministration);
			session.save(administration);
			session.beginTransaction().commit();
			session.close();
			
		}
		
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
