package com.ims.data;

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
@Table(name="Student_Other_Qulification")
public class StudentOtherQulification {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int otherQulificationId;

	private String description;

	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="studentUserName")
	private Student student;
	
	
	
	//getters and setters
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setOtherQulificationId(int otherQulificationId) {
		this.otherQulificationId = otherQulificationId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public int getOtherQulificationId() {
		return otherQulificationId;
	}
	

}
