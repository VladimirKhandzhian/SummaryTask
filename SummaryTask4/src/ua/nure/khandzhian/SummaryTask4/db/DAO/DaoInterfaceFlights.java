package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;

public interface DaoInterfaceFlights {
	
	public List<Flights> getFlights() throws Exception;
	
	public Flights findFlightById(int id) throws Exception;
	
	public List<Flights> getFlightsByWorkerId(int workerId) throws Exception;
	
	public void updateStatusFlight(int id, String flightStatus) throws Exception;

	public void deleteFlight(int id) throws Exception;

	public void updateFlight(Flights flights) throws Exception;

	public void insertFlight(Flights flights) throws Exception;

	public List<Flights> getFlightsByNumber(String flightNumber) throws Exception;

	public List<Flights> getFlightsByDirection(Flights flight) throws Exception;

	public List<Flights> getFlightsByIdDesc() throws Exception;
	
	public List<Flights> getFlightsByIdAsc() throws Exception;

	public List<Flights> getFlightsByNameDesc() throws Exception;
	
	public List<Flights> getFlightsByNameAsc() throws Exception;

	public int getDayBeforeDeparture(int flightId) throws Exception;

}
