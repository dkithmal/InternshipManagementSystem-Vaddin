package com.ims.data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class StudentCompanyId implements java.io.Serializable {
	
	@ManyToOne
	private Student student;
	@ManyToOne
	private Company company;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// getters and setters
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

}
