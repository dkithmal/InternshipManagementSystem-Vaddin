package com.ims.business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Administration;
import com.ims.data.User;

public class AdminDAO {
	
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
	
	
	public void updateCurrentStudentBatch(int newStudentCurrentBatch)
	{

        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Administration ";
        Query query = session.createQuery(SQL_QUERY);
        List<Administration> list = ((org.hibernate.Query) query).list();
        session.close();



        if(list.size()>0)
        {
            Administration administration= list.get(0);
            administration.setCurrentBatch(newStudentCurrentBatch);

            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.update(administration);
            session2.beginTransaction().commit();
            session2.close();

        }
        else
        {

            Administration administration= new Administration();
            administration.setCurrentBatch(newStudentCurrentBatch);
            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.save(administration);
            session2.beginTransaction().commit();
            session2.close();

        }

		
	}


    public void enableStudentLogin()
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Administration ";
        Query query = session.createQuery(SQL_QUERY);
        List<Administration> list = ((org.hibernate.Query) query).list();
        session.close();



        if(list.size()>0)
        {
            Administration administration= list.get(0);
            administration.setAllowStudnetToLog(true);

            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.update(administration);
            session2.beginTransaction().commit();
            session2.close();

        }
        else
        {

            Administration administration= new Administration();
            administration.setAllowStudnetToLog(true);
            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.save(administration);
            session2.beginTransaction().commit();
            session2.close();

        }

    }

    public void disableStudentLogin()
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Administration ";
        Query query = session.createQuery(SQL_QUERY);
        List<Administration> list = ((org.hibernate.Query) query).list();
        session.close();



        if(list.size()>0)
        {
            Administration administration= list.get(0);
            administration.setAllowStudnetToLog(false);

            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.update(administration);
            session2.beginTransaction().commit();
            session2.close();

        }
        else
        {

            Administration administration= new Administration();
            administration.setAllowStudnetToLog(false);
            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.save(administration);
            session2.beginTransaction().commit();
            session2.close();

        }

    }


    public boolean getStudentLoginState()
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Administration ";
        Query query = session.createQuery(SQL_QUERY);
        List<Administration> list = ((org.hibernate.Query) query).list();
        session.close();

        if(list.size()>0)
        {

            return list.get(0).isAllowStudnetToLog();

        }
        else
        {
            return false;
        }

    }

    public void setStudentApplicableCompanyCount(int count)
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Administration ";
        Query query = session.createQuery(SQL_QUERY);
        List<Administration> list = ((org.hibernate.Query) query).list();
        session.close();



        if(list.size()>0)
        {
            Administration administration= list.get(0);
            administration.setApplicableCompanyCount(count);

            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.update(administration);
            session2.beginTransaction().commit();
            session2.close();

        }
        else
        {

            Administration administration= new Administration();
            administration.setApplicableCompanyCount(count);
            Session session2 = getSessionFactory().openSession();
            session2.beginTransaction();
            session2.save(administration);
            session2.beginTransaction().commit();
            session2.close();

        }

    }
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
