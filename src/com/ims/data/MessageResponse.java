package com.ims.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@Table(name="Message_Response")
public class MessageResponse {
	
	
	private int msgId;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int responseMsgId;
	private String senderId;
	private String subject;
	private String content;
	private String date;
	private String time;
	
	
	
	
	
	
	
	
	
	//getters and setters
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public int getResponseMsgId() {
		return responseMsgId;
	}
	public void setResponseMsgId(int responseMsgId) {
		this.responseMsgId = responseMsgId;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
	

}
