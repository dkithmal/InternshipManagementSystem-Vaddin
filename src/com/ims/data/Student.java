package com.ims.data;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;





@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name="Student")

public class Student {
	
	@Id
	private String studentUserName;
	private String nameInFull;
	private String nameWithInitials;
	private String objectives;
	private String profile;
	private String gender;
	private String dateOfBirth;
	private String maritalStatus;
	private String permanentAddress;
	private String mobile1;
	private String mobile2;
	private String telephone;
	private String email;
	private String school;
	private String alSubject1;
	private String alSubject2;
	private String alSubject3;
	private String alResult1;
	private String alResult2;
	private String alResult3;
    private String digreeTitle;
	private int yearOfAdmission;
    private int studentBatch;
	private float gpaSemester1;
	private float gpaSemester2;
	private float gpaSemester3;
	// this variable is used to get state of student selected or not to view to other companyis that student applied
	private boolean selected;
    private String selectedCompanyName;



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.student", cascade=CascadeType.ALL)
	private Collection<StudentAppliedCompany> studentCompany = new ArrayList<StudentAppliedCompany>();
	

	@OneToMany(mappedBy="student",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	private  Collection<StudentComplitedProjects> studentComplitedProjects = new ArrayList<StudentComplitedProjects>();
	
	@OneToMany(mappedBy="student",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	private Collection<StudentOtherQulification> studentOtherQulification = new ArrayList<StudentOtherQulification>();
	
	@OneToMany(mappedBy="student",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	private Collection<StudentProfessionalExp> studentProfessionalExp = new ArrayList<StudentProfessionalExp>();
	
/*	@OneToMany(mappedBy="student",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	private  Collection<Support> support  = new ArrayList<Support>();*/
	
	
	
	
	
	//getters and setters
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Collection<StudentAppliedCompany> getStudentCompany() {
		return studentCompany;
	}

	public void setStudentCompany(Collection<StudentAppliedCompany> studentCompany) {
		this.studentCompany = studentCompany;
	}
    public Collection<StudentOtherQulification> getStudentOtherQulification() {
		return studentOtherQulification;
	}

	public void setStudentOtherQulification(
			Collection<StudentOtherQulification> studentOtherQulification) {
		this.studentOtherQulification = studentOtherQulification;
	}

	public Collection<StudentProfessionalExp> getStudentProfessionalExp() {
		return studentProfessionalExp;
	}

	public void setStudentProfessionalExp(
			Collection<StudentProfessionalExp> studentProfessionalExp) {
		this.studentProfessionalExp = studentProfessionalExp;
	}

	public String getStudentUserName() {
		return studentUserName;
	}

	public Collection<StudentComplitedProjects> getStudentComplitedProjects() {
		return studentComplitedProjects;
	}

	public void setStudentComplitedProjects(
			Collection<StudentComplitedProjects> studentComplitedProjects) {
		this.studentComplitedProjects = studentComplitedProjects;
	}

	public void setStudentUserName(String studentUserName) {
		this.studentUserName = studentUserName;
	}
	public String getNameInFull() {
		return nameInFull;
	}
	public void setNameInFull(String nameInFull) {
		this.nameInFull = nameInFull;
	}
	public String getNameWithInitials() {
		return nameWithInitials;
	}
	public void setNameWithInitials(String nameWithInitials) {
		this.nameWithInitials = nameWithInitials;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getAlSubject1() {
		return alSubject1;
	}
	public void setAlSubject1(String alSubject1) {
		this.alSubject1 = alSubject1;
	}
	public String getAlSubject2() {
		return alSubject2;
	}
	public void setAlSubject2(String alSubject2) {
		this.alSubject2 = alSubject2;
	}
	public String getAlSubject3() {
		return alSubject3;
	}
	public void setAlSubject3(String alSubject3) {
		this.alSubject3 = alSubject3;
	}
	public String getAlResult1() {
		return alResult1;
	}
	public void setAlResult1(String alResult1) {
		this.alResult1 = alResult1;
	}
	public String getAlResult2() {
		return alResult2;
	}
	public void setAlResult2(String alResult2) {
		this.alResult2 = alResult2;
	}
	public String getAlResult3() {
		return alResult3;
	}
	public void setAlResult3(String alResult3) {
		this.alResult3 = alResult3;
	}
	public String getDigreeTitle() {
		return digreeTitle;
	}
	public void setDigreeTitle(String digreeTitle) {
		this.digreeTitle = digreeTitle;
	}

	public int getYearOfAdmission() {
		return yearOfAdmission;
	}
	public void setYearOfAdmission(int yearOfAdmission) {
		this.yearOfAdmission = yearOfAdmission;
	}
	public float getGpaSemester1() {
		return gpaSemester1;
	}
	public void setGpaSemester1(float gpaSemester1) {
		this.gpaSemester1 = gpaSemester1;
	}
	public float getGpaSemester2() {
		return gpaSemester2;
	}
	public void setGpaSemester2(float gpaSemester2) {
		this.gpaSemester2 = gpaSemester2;
	}
	public float getGpaSemester3() {
		return gpaSemester3;
	}
	public void setGpaSemester3(float gpaSemester3) {
		this.gpaSemester3 = gpaSemester3;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}


    public String getSelectedCompanyName() {
        return selectedCompanyName;
    }

    public void setSelectedCompanyName(String selectedCompanyName) {
        this.selectedCompanyName = selectedCompanyName;
    }


    public int getStudentBatch() {
        return studentBatch;
    }

    public void setStudentBatch(int studentBatch) {
        this.studentBatch = studentBatch;
    }

}
