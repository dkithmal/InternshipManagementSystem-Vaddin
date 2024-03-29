package com.ims.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name="Administration")
public class Administration {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int administrationId;
	private int currentBatch;
    private boolean allowStudentToLog;
    private int applicableCompanyCount;







//gettsrs and setters
public int getAdministrationId() {
    return administrationId;
}

    public void setAdministrationId(int administrationId) {
        this.administrationId = administrationId;
    }


	public int getCurrentBatch() {
		return currentBatch;
	}

	public void setCurrentBatch(int batch) {
		this.currentBatch = batch;
	}
    public boolean isAllowStudentToLog() {
        return allowStudentToLog;
    }

    public void setAllowStudentToLog(boolean allowStudnetToLog) {
        this.allowStudentToLog = allowStudnetToLog;
    }

    public int getApplicableCompanyCount() {
        return applicableCompanyCount;
    }

    public void setApplicableCompanyCount(int applicableCompanyCount) {
        this.applicableCompanyCount = applicableCompanyCount;
    }

	
	

	
	

}
