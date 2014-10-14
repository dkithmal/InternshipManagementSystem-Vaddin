package com.ims.data;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
@Table(name = "Student_Company")
@AssociationOverrides({
		@AssociationOverride(name = "pk.student", 
			joinColumns = @JoinColumn(name = "studentUserName")),
		@AssociationOverride(name = "pk.company", 
			joinColumns = @JoinColumn(name = "companyUserName")) })
public class StudentCompany implements Serializable{
	
	

	@EmbeddedId
	private StudentCompanyId pk = new StudentCompanyId();
	private String state;
    private int batch;








    @Transient

	public Student getStudent() {
		return getPk().getStudent();
	}
 
	public void setStudent(Student student) {
		getPk().setStudent(student);
	}
 
	@Transient
	public Company getCompany() {
		return getPk().getCompany();
	}
	
	public void setCompany(Company company) {
		getPk().setCompany(company);
	}
	
	
	
	
	
	
	
	
	
	//getters and setters
	public StudentCompanyId getPk() {
		return pk;
	}
	public void setPk(StudentCompanyId pk) {
		this.pk = pk;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

}
