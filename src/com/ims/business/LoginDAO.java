package com.ims.business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Administration;
import com.ims.data.Company;
import com.ims.data.User;

public class LoginDAO {
	
	private SessionFactory sessionFactory;

	
	public String findUser(String userName,String password)
	{
		Session session = getSessionFactory().openSession();
		String hql = " from User u where u.userName=? and u.password=? ";
		Query query = session.createQuery(hql);
		

		List<User> usersList = query.setString(0, userName).setString(1, password).list();
		if(usersList.size()>0)
		{
			if(usersList.get(0).getType()=='a')
			{
				return "Admin";
				
			}
			else if(usersList.get(0).getType()=='s')
			{
				Administration oldAdministration= (Administration)session.get(Administration.class, 1234);
				if(oldAdministration==null)
				{
					return "error";
				}
				if(oldAdministration.isAllowStudnetToLog()&&usersList.get(0).getBatch()==oldAdministration.getCurrentBatch())
				{
					if(usersList.get(0).getPrivilage()=='A')
					{
						return "Student-All";
						
					}
					else
					{
						return "Student-ViewOnly";
						
					}
					
				}
				else
				{
					return "error";
				}
/*
				String hql2 = " from Student st where st.studentUserName='" + userName + "'";
				Query query2 = session.createQuery(hql2);
				List<Student> list2 = ((org.hibernate.Query) query2).list();
				if (list2.size() > 0) {
					session.close();

					return "registedStudent";
				}

				return "notRegistedStudent";*/
				

				
			}
			else 
			{
				String hql2 = " from Company cm where cm.companyUserName='" + userName + "'and cm.allowed=true";
				Query query2 = session.createQuery(hql2);
				List<Company> list2 = ((org.hibernate.Query) query2).list();
				if (list2.size() > 0) {
					session.close();

					//return "allowedCompany";
					
					if(usersList.get(0).getPrivilage()=='A')
					{
						return "Company-All";
						
					}
					else if(usersList.get(0).getPrivilage()=='V')
					{
						return "Company-ViewOnly";
						
					}
					else
					{
						return "Company-SelectOnly";
						
					}
				}

				session.close();
				return "Comany-NotAllowed";
				//return "notallowedCompany";
				
			}
			
		}
		else
		{
			session.close();
			return "error";
			
		}
		
	}
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
