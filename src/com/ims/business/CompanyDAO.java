package com.ims.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Company;
import com.ims.data.Student;
import com.ims.data.StudentAppliedCompany;

public class CompanyDAO {
	private SessionFactory sessionFactory;

	
	
	public List<Company> getAllowedComapnies()
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from Company as com  where com.allowed='1'";
		Query query = session.createQuery(SQL_QUERY);
		List<Company> list = ((org.hibernate.Query) query).list();
		session.close();

		return list;
		
	}
	
	public List<Company> getNotAllowedCompanies()
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from Company as com  where com.allowed='0'";
		Query query = session.createQuery(SQL_QUERY);
		List<Company> list = ((org.hibernate.Query) query).list();
		session.close();

		return list;
		
	}
	
	
	public Company getComapny(String userName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Company company =(Company)session.get(Company.class, userName);
		session.getTransaction().commit();
		session.close();
		
		return company;
		
	}
	
	
	public Company getCompanyFromCompanyName(String name)
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from Company as com  where com.companyName='" + name + "'";
		Query query = session.createQuery(SQL_QUERY);
		List<Company> list = ((org.hibernate.Query) query).list();
		session.close();

		if(list.size()>0)
			return list.get(0);
		else 
			return null;
		
		
	}
	
	public void acceptCompany(String userName)
	{
		Session session = getSessionFactory().openSession();		
		session.beginTransaction();		
		Company company = (Company)session.get(Company.class, userName);
		company.setAllowed(true);	
		session.merge(company);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public void unAllowedCompany(String userName)
	{
		Session session = getSessionFactory().openSession();		
		session.beginTransaction();		
		Company company = (Company)session.get(Company.class, userName);
		company.setAllowed(false);	
		session.merge(company);
		session.getTransaction().commit();
		session.close();
		
	}
	
	//this is created for not allowed company deletion but i think need to think about romoving form all tables
	public void removeCompany(String userName)
	{
		
	}
	
	
	public boolean getCompanyState(String Companyname)
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from Company as com  where com.companyName='" + Companyname + "'";
		Query query = session.createQuery(SQL_QUERY);
		List<Company> list = ((org.hibernate.Query) query).list();
		session.close();

		if(list.size()>0)
			return list.get(0).isAllowed();
		else 

			return false;
				
	}
	
	public List<Student> getAppliedStudentToCompany(String userName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Company company =(Company)session.get(Company.class, userName);
		int x= company.getStudentCompany().size();
		session.getTransaction().commit();
		session.close();
		
		List<Student> list= new ArrayList<Student>();
		if(company.isReceiveCv()==true)
		{
			for(StudentAppliedCompany studentAppliedCompany:company.getStudentCompany())
			{
				list.add(studentAppliedCompany.getStudent());
				
			}
			
		}
		else
		{
			list= null;
		}
		

		
		return list;
		
	}
	
	public List<Student> getNotSelectStudentForInterview(String userName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Company company =(Company)session.get(Company.class, userName);
		int x= company.getStudentCompany().size();
		session.getTransaction().commit();
		session.close();
		
		List<Student> list= new ArrayList<Student>();
		if(company.isReceiveCv()==true)
		{
			for(StudentAppliedCompany studentAppliedCompany:company.getStudentCompany())
			{
				if(studentAppliedCompany.getState().equals("pending"))
				list.add(studentAppliedCompany.getStudent());
				
			}
			
		}
		else
		{
			list= null;
		}
		

		
		return list;
		
	}
	
	public List<Student> getSelectedStudentForInterview(String userName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Company company =(Company)session.get(Company.class, userName);
		int x= company.getStudentCompany().size();
		session.getTransaction().commit();
		session.close();
		
		List<Student> list= new ArrayList<Student>();
		if(company.isReceiveCv()==true)
		{
			for(StudentAppliedCompany studentAppliedCompany:company.getStudentCompany())
			{
				
				if(studentAppliedCompany.getState().equals("interview"))
				{
					list.add(studentAppliedCompany.getStudent());
					
					
				}
				
				
			}
			
		}
		else
		{
			list= null;
		}
		

		
		return list;
		
	}
	
	public List<Student> getSelectedStudentForInternship(String userName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Company company =(Company)session.get(Company.class, userName);
		int x= company.getStudentCompany().size();
		session.getTransaction().commit();
		session.close();
		
		List<Student> list= new ArrayList<Student>();
		if(company.isReceiveCv()==true)
		{
			for(StudentAppliedCompany studentAppliedCompany:company.getStudentCompany())
			{
				
				if(studentAppliedCompany.getState().equals("Selected"))
				{
					list.add(studentAppliedCompany.getStudent());
					
					
				}
				
				
			}
			
		}
		else
		{
			list= null;
		}
		

		
		return list;
		
	}
	
	
	
	
	
	
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
