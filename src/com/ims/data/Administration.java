package com.ims.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name="Administration")
public class Administration {
	
	@Id 
	private int adminId;
	private int batch;


	//gettsrs and setters
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}
	
	
	
	
	
	
	
	

}
