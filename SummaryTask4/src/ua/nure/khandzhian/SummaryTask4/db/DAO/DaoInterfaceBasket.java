package ua.nure.khandzhian.SummaryTask4.db.DAO;

import java.util.List;

import ua.nure.khandzhian.SummaryTask4.db.entity.Basket;

public interface DaoInterfaceBasket {

	public List<Basket> getTicketByFlightId(int flightId) throws Exception;

	public List<Basket> getBasketByClientId(Basket basket) throws Exception;

	public void deleteFromBasket(Basket basket) throws Exception;

	public void insertIntoBasket(Basket basket) throws Exception;

	public double getSumToPaid(Basket basket) throws Exception;

	public void deleteFromBasketBySessionId(String sessionId) throws Exception;

}
