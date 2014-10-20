package com.ims.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name="Student_Complited_Projects")
public class StudentComplitedProjects {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int projectId;
	private String projectTitle;
	private String projectDescription;
	 @Column(name="projectYear")
	 private String projectYear;

	@ManyToOne()
	@JoinColumn(name="studentUserName")
	private Student student;
	
	
	

	
	
	
	//getters and setters
		
		

		public int getProjectId() {
			return projectId;
		}
		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}
		public Student getStudent() {
			return student;
		}
		public void setStudent(Student student) {
			this.student = student;
		}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getProjectYear() {
		return projectYear;
	}
	public void setProjectYear(String projectYear) {
		this.projectYear = projectYear;
	}
	
	
	

}
