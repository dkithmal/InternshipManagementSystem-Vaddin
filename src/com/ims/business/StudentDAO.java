package com.ims.business;

import java.util.List;

import com.ims.data.Administration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Student;
import com.ims.data.User;

public class StudentDAO {
	
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

	
	public List<Student> getAllStudents()
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from Student as stu where stu.studentBatch="+getCurrentStudentBatch()+"";
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



    public List<Student> getStudentsSelectedForInternship()
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Student as stu where stu.studentBatch="+getCurrentStudentBatch()+" and stu.selected=true";
        Query query = session.createQuery(SQL_QUERY);
        List<Student> list = ((org.hibernate.Query) query).list();

        session.close();
        return list;

    }

    public List<Student> getStudentsNotSelectedForInternship()
    {
        Session session = getSessionFactory().openSession();
        String SQL_QUERY = "from Student as stu where stu.studentBatch="+getCurrentStudentBatch()+" and stu.selected=false";
        Query query = session.createQuery(SQL_QUERY);
        List<Student> list = ((org.hibernate.Query) query).list();

        session.close();
        return list;

    }

    public Student getStudentCvInfomation(String userName)
    {


        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Student student =(Student)session.get(Student.class, userName);
        // this x , y initialize becose othervise its not fetch the object from this tables
        if(student==null)
        {
            return null;
        }
        int x =student.getStudentOtherQulification().size();
        int y =student.getStudentComplitedProjects().size();
        int z= student.getStudentCompany().size();
        session.getTransaction().commit();
        session.close();

        return student;


    }


    public Student getStudent(String userName)
    {


        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Student student =(Student)session.get(Student.class, userName);
        // this x , y initialize becose othervise its not fetch the object from this tables
        if(student==null)
        {
            return null;
        }
        int x =student.getStudentOtherQulification().size();
        int y =student.getStudentComplitedProjects().size();
        int z= student.getStudentCompany().size();
        session.getTransaction().commit();
        session.close();

        return student;


    }

    public void updateStudent(Student studentUpdated)
    {

        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.update(studentUpdated);
        session.getTransaction().commit();
        session.close();


    }

	
	
	
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
