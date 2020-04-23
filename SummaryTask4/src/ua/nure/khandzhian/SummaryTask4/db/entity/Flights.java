package ua.nure.khandzhian.SummaryTask4.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;

public class Flights extends Airplane implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String flightNumber;
	private String nameOfFlight;
	private String departure;
	private String destination;
	private LocalDateTime departureDateTime;
	private LocalDateTime destinationDateTime;
	private int idAirplane;
	private int emptyOfSeats;
	private double price;
	private String flightStatus;
	private int idBrigad;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public String getNameOfFlight() {
		return nameOfFlight;
	}
	
	public void setNameOfFlight(String nameOfFlight) {
		this.nameOfFlight = nameOfFlight;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public LocalDateTime getDestinationDateTime() {
		return destinationDateTime;
	}

	public void setDestinationDateTime(LocalDateTime destinationDateTime) {
		this.destinationDateTime = destinationDateTime;
	}
	

	public int getIdAirplane() {
		return idAirplane;
	}

	public void setIdAirplane(int idAirplane) {
		this.idAirplane = idAirplane;
	}
	
	public int getEmptyOfSeats() {
		return emptyOfSeats;
	}

	public void setEmptyOfSeats(int emptyOfSeats) {
		this.emptyOfSeats = emptyOfSeats;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

	public int getIdBrigad() {
		return idBrigad;
	}

	public void setIdBrigad(int idBrigad) {
		this.idBrigad = idBrigad;
	}

	@Override
	public String toString() {
		return "Flights [id=" + id + ", flightNumber=" + flightNumber + ", nameOfFlight=" + nameOfFlight
				+ ", departure=" + departure + ", destination=" + destination + ", departureDateTime="
				+ departureDateTime + ", destinationDateTime=" + destinationDateTime + ", idAirplane=" + idAirplane
				+ ", emptyOfSeats=" + emptyOfSeats + ", price=" + price + ", flightStatus=" + flightStatus
				+ ", idBrigad=" + idBrigad + "]";
	}
	
	public List<Flights> getFlights() throws Exception{
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		return daoInterfaceFlights.getFlights();
	}

}
