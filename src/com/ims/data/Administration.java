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
	private int currentBatch;
    private boolean allowStudnetToLog;



    //gettsrs and setters
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getCurrentBatch() {
		return currentBatch;
	}

	public void setCurrentBatch(int batch) {
		this.currentBatch = batch;
	}
    public boolean isAllowStudnetToLog() {
        return allowStudnetToLog;
    }

    public void setAllowStudnetToLog(boolean allowStudnetToLog) {
        this.allowStudnetToLog = allowStudnetToLog;
    }
	
	
	
	
	
	
	
	

}
