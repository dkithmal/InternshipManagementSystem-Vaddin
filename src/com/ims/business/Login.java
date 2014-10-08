package com.ims.business;

import org.hibernate.SessionFactory;

public class Login {
	
	private SessionFactory sessionFactory;

	
	
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
