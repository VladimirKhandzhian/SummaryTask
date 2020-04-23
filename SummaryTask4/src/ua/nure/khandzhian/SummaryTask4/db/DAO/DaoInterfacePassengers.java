package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;

public interface DaoInterfacePassengers {

	public List<Passengers> getPassengers() throws Exception;

	public Passengers findPassengerByLogin(String login) throws Exception;

	public Passengers findPassengerById(String passportId) throws Exception;
	
	public void insertPassenger(Passengers passenger) throws Exception;

	public void updatePassenger(Passengers passenger, String oldPassportId) throws Exception;

	public void deletePassenger(String passportId) throws Exception;

}
