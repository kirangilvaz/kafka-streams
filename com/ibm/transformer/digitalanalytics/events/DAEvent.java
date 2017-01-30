package com.ibm.transformer.digitalanalytics.events;

import java.io.Serializable;
import java.util.Date;

public class DAEvent implements Serializable {
	public static final long serialVersionUID = 1L;
	public String clientID = "";
	public String cookieID = "";
	public String sessionID = "";
	public Date timestamp = new Date();
	public String tagID = "";
	public String eventCode = "";
	
	public DAEvent(){
		super();
	}
	public DAEvent(String eventCode, String clientID, String cookieID, String sessionID,
			Date timestamp, String tagID) {
		super();
		this.eventCode = eventCode;
		this.clientID = clientID;
		this.cookieID = cookieID;
		this.sessionID = sessionID;
		this.timestamp = timestamp;
		this.tagID = tagID;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getTagID() {
		return tagID;
	}
	public void setTagID(String tagID) {
		this.tagID = tagID;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getCookieID() {
		return cookieID;
	}
	public void setCookieID(String cookieID) {
		this.cookieID = cookieID;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
}
