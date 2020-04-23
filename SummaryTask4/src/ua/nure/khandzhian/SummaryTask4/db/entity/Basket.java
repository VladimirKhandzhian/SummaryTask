package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;

public class Basket extends Flights implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private int ticketId;
	private String passportId;
	private String sessionId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public String getPassportId() {
		return passportId;
	}
	
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "Basket [Id=" + id + ", ticketId=" + ticketId + ", passportId=" + passportId + ", sessionId=" 
				+ sessionId + "]";
	}
		
}
