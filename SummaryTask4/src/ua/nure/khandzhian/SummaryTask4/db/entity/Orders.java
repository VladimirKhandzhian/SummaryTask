package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;

public class Orders extends Flights implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int ticketId;
	private String passportId;
	private String firstName;
	private String secondName;
	private String email;
	private double price;
	
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
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", ticketId=" + ticketId + ", passportId=" + passportId + ", firstName=" + firstName
				+ ", secondName=" + secondName + ", email=" + email + ", price=" + price + "]";
	}
	
}
