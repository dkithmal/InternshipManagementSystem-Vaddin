package com.ims.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name="Support")
public class Support {
	
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int supportMessageId;
	private String senderEmail;
	private String senderUserName;
	private String senderMessage;
	private String senderName;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	//getters and setters
	public int getSupportMessageId() {
		return supportMessageId;
	}
	public void setSupportMessageId(int supportMessageId) {
		this.supportMessageId = supportMessageId;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getSenderUserName() {
		return senderUserName;
	}
	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}
	public String getSenderMessage() {
		return senderMessage;
	}
	public void setSenderMessage(String senderMessage) {
		this.senderMessage = senderMessage;
	}
	
	
	
	
	
	

}
