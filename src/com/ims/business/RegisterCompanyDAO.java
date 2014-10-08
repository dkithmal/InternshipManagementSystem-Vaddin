package com.ims.business;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Company;
import com.ims.data.User;

public class RegisterCompanyDAO {
	
	private SessionFactory sessionFactory;

	
	
	public void saveNewComapny(Company company,User user)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(company);
		session.beginTransaction().commit();
		session.close();
		
		
		Session session2 = getSessionFactory().openSession();
		session2.beginTransaction();
		session2.save(user);
		session2.beginTransaction().commit();
		session2.close();
		
	}
	
	
	
	public Company getExistingCompany(String userName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Company company =(Company)session.get(Company.class, userName);
		session.getTransaction().commit();
		session.close();
		
		return company;
		
		
		
	}
	
	public User getExistingUser(String userName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		User user= (User)session.get(User.class, userName);
		session.getTransaction().commit();
		session.close();
		
		return user;
		
	}
	
	
	public void updateCompany(Company companyUpdated,User userUpdated)
	{
		Session session = getSessionFactory().openSession();		
		session.beginTransaction();		
		session.update(companyUpdated);
		session.update(userUpdated);
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	public boolean cheackCompanuyUserNameAlerayAvailable(String userName)
	{

		Session session = getSessionFactory().openSession();
		String hql = " from User u where u.userName='" + userName + "'";
		Query query = session.createQuery(hql);
		
		Iterator<User> it = ((org.hibernate.Query) query).iterate();
		List<User> list = ((org.hibernate.Query) query).list();

		session.close();
		if (list.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
