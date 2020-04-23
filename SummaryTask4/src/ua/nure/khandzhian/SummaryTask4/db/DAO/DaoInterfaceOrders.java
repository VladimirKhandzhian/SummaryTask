package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;

public interface DaoInterfaceOrders {

	public void insertOrders(Orders order) throws Exception;

	public List<Orders> getOrdersByPassportId(Orders order) throws Exception;

	public List<Orders> getPassengersByFlightId(Orders order) throws Exception;

	public int getCountOfTocketByFlightId(int id) throws Exception;

	public List<Orders> getInformationAboutTicket(Orders order) throws Exception;

	public Orders getInformationAboutFlights(Orders order) throws Exception;

	
}
