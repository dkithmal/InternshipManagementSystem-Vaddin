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

    private int getCurrentStudentBatch()
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Administration ";
        Query query = session.createQuery(SQL_QUERY);
        List<Administration> list = ((org.hibernate.Query) query).list();
        session.close();

        if(list.size()>0)
        {
            return list.get(0).getCurrentBatch();

        }
        else
        {
            return 0;

        }

    }

    private boolean isStudentAllowToLog()
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Administration ";
        Query query = session.createQuery(SQL_QUERY);
        List<Administration> list = ((org.hibernate.Query) query).list();
        session.close();

        if(list.size()>0)
        {
            return list.get(0).isAllowStudentToLog();

        }
        else
        {
            return false;

        }

    }

	
	public String findUser(String userName,String password)
	{
		Session session = getSessionFactory().openSession();
        System.out.println("User Name   "+userName);
        List<User> usersList=session.createQuery(" from User u where u.userName=? and u.password=? ").setParameter(0,userName).setParameter(1, password).list();
		if(usersList.size()>0)
		{
			if(usersList.get(0).getType()=='a')
			{
				return "Admin";
				
			}
			else if(usersList.get(0).getType()=='s')
			{

                int currentBatch=getCurrentStudentBatch();
				if(currentBatch==0)
				{
					return "error";
				}
				if(isStudentAllowToLog()&&usersList.get(0).getBatch()==currentBatch)
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
				return "Company-NotAllowed";
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
