package com.ims.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name="Messages")
public class Messages {
	
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int msgId;
  	private String senderId;
	private String receiveId;
	private String subject;
	private String content;
	private String date;
	private String msgCategory;
	private String checked;
	//private String time;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//getters and setters
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public String getMsgCategory() {
		return msgCategory;
	}
	public void setMsgCategory(String msgCategory) {
		this.msgCategory = msgCategory;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}

	
	
//	public String getTime() {
//		return time;
//	}
//	public void setTime(String time) {
//		this.time = time;
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
