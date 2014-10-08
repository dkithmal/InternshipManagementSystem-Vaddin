package com.ims.business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Student;
import com.ims.data.User;

public class StudentDAO {
	
	private SessionFactory sessionFactory;

	
	public List<Student> getAllStudents()
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from Student";
		Query query = session.createQuery(SQL_QUERY);
		List<Student> list = ((org.hibernate.Query) query).list();

		session.close();
		return list;
		
	}
	
	
	public void createSingleStudentProfile(User user)
	{
		Session session2 = getSessionFactory().openSession();
		session2.beginTransaction();
		session2.save(user);
		session2.beginTransaction().commit();
		session2.close();
		
	}
	
	
	
	
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
