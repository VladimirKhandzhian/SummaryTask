package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;
import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoAirplanes;

public class Airplane implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idAirplane;
	private String model;
	private int numberOfSeats;
	
	public int getIdAirplane() {
		return idAirplane;
	}
	
	public void setIdAirplane(int idAirplane) {
		this.idAirplane = idAirplane;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	@Override
	public String toString() {
		return "Airplane [IdAirplane: " + idAirplane + ", " + 
				"Model: " + model + ", " +
				"Number of seats:  " + numberOfSeats + "]";
	}
	
	public List<Airplane> getAirplane() throws Exception{
		DaoInterfaceAirplanes daoInterfaceAirplanes = new DaoAirplanes();
		return daoInterfaceAirplanes.getAirplane();
	}

}
