package com.ims.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ims.data.Company;
import com.ims.data.Student;
import com.ims.data.StudentAppliedCompany;
import com.ims.data.StudentAppliedCompanyId;

public class StudentAppliedCompanyDAO {
	private SessionFactory sessionFactory;

	
	
	public void updateStudentStateToInterview(String compnayUserName,String studentUserName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Student student =(Student)session.get(Student.class, studentUserName);
		// this x , y initialize becose othervise its not fetch the object from this tables
		int x= student.getStudentCompany().size();
		
		Company company =(Company)session.get(Company.class, compnayUserName);
		int y= company.getStudentCompany().size();
		
		session.getTransaction().commit();
		session.close();
		
	
		
		StudentAppliedCompanyId studentCompanyId = new StudentAppliedCompanyId();
		studentCompanyId.setStudent(student);
		studentCompanyId.setCompany(company);
		
		Session session2 = getSessionFactory().openSession();
		session2.beginTransaction();
		StudentAppliedCompany studentCompany =(StudentAppliedCompany)session2.get(StudentAppliedCompany.class, studentCompanyId);
		studentCompany.setState("interview");
		session2.update(studentCompany);
		session2.getTransaction().commit();
		session2.close();
		
	
		
	}
	
	public void updateStudentStateToSelected(String compnayUserName,String studentUserName)
	{
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

        Company company =(Company)session.get(Company.class, compnayUserName);
        int y= company.getStudentCompany().size();

		Student student =(Student)session.get(Student.class, studentUserName);
		// this x , y initialize becose othervise its not fetch the object from this tables
		int x= student.getStudentCompany().size();
		student.setSelected(true);
        student.setSelectedCompanyName(company.getCompanyName());
		session.update(student);
		

		
		session.getTransaction().commit();
		session.close();
		
	
		
		StudentAppliedCompanyId studentCompanyId = new StudentAppliedCompanyId();
		studentCompanyId.setStudent(student);
		studentCompanyId.setCompany(company);
		
		Session session2 = getSessionFactory().openSession();
		session2.beginTransaction();
		StudentAppliedCompany studentCompany =(StudentAppliedCompany)session2.get(StudentAppliedCompany.class, studentCompanyId);
		studentCompany.setState("Selected");
		session2.update(studentCompany);
		session2.getTransaction().commit();
		session2.close();
		
	
		
	}
	
	
	public boolean checkStudentAppliedComapany(String companyUserName,String studentUserName)
	{
		Session session = getSessionFactory().openSession();
		String SQL_QUERY = "from StudentAppliedCompany as sac  where sac.pk.company.companyUserName='" + companyUserName + "' and  sac.pk.student.studentUserName='" + studentUserName + "'";
		Query query = session.createQuery(SQL_QUERY);
		List<StudentAppliedCompany> list = ((org.hibernate.Query) query).list();
		session.close();

		if(list.size()>0)
			return true;
		//return list;
		
		return false;
	}

    public List<StudentAppliedCompany> getStudentAppliedCompanyList(String userName)
    {

        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Student student =(Student)session.get(Student.class, userName);
        // this x , y initialize becose othervise its not fetch the object from this tables
        int x =student.getStudentCompany().size();
        System.out.println(x+"size of student company");

/*		StudentCompany  studentCompany = new StudentCompany();
		studentCompany.setStudent(student);
		studentCompany.setCompany(null);
		String SQL_QUERY = "from StudentCompany as stucom  where stucom.pk='" + studentCompany + "'";
		Query query = session.createQuery(SQL_QUERY);
		List<StudentCompany> list = ((org.hibernate.Query) query).list();*/
        session.getTransaction().commit();
        session.close();
        List<StudentAppliedCompany> list = new ArrayList<StudentAppliedCompany>();
        list.addAll(student.getStudentCompany());

        if(list.size()==0)
        {
            System.out.println("mala not working");
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
