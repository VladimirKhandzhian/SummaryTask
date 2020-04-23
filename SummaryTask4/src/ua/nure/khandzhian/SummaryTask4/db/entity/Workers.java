package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;
import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceRequests;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoRequests;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;

public class Workers extends Status implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idWorker;
	private String firstName;
	private String secondName;
	private String phoneNumber;
	private String email;
	private int idStatus;
	private String login;
	private String password;
	private String profession;
	
	public int getIdWorker() {
		return idWorker;
	}
	
	public void setIdWorker(int idWorker) {
		this.idWorker = idWorker;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Override
	public String toString() {
		return "Workers [idWorker=" + idWorker + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", idStatus=" + idStatus + ", login=" + login
				+ ", password=" + password + ", profession=" + profession + "]";
	}
	
	public List<Workers> getWorkers() throws Exception{
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		return daoInterfaceWorkers.getWorkers();
	}
	
	public List<Workers> getEmptyWorker() throws Exception{
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		return daoInterfaceWorkers.getEmptyWorker();
	}
	
	public List<Workers> getEmptyCommander() throws Exception{
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		return daoInterfaceWorkers.getEmptyCommander();
	}
	
	public List<Flights> getFlightsByWorkerId() throws Exception{
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		return daoInterfaceFlights.getFlightsByWorkerId(getIdWorker());
	}
	
	public List<Requests> getRequestsByWorkerId() throws Exception{
		DaoInterfaceRequests daoInterfaceRequest = new DaoRequests();
		return daoInterfaceRequest.getRequestsByWorkerId(getIdWorker());
	}

}
