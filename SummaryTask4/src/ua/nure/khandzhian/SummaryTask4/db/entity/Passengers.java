package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;

public class Passengers extends Status implements Serializable{

	private static final long serialVersionUID = 1L;

	private String passportId;
	private String firstName;
	private String secondName;
	private String email;
	private int idStatus;
	private String login;
	private String password;
	
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

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Passengers [passportId=" + passportId + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", email=" + email + ", idStatus=" + idStatus + ", login=" + login + ", password=" + password + "]";
	}
	
}
