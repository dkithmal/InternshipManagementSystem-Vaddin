package com.ims.business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Student;

public class RegisterStudentDAO {
	
	
	private SessionFactory sessionFactory;
	
	
	
	public void registerNewStudent(Student studnet)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(studnet);
		session.beginTransaction().commit();
		session.close();
		
	}
	
	
	
	
	
	
	
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
