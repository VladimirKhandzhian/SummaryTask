package ua.nure.khandzhian.SummaryTask4.web.command.client;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.createTicket.TicketCreator;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBasket;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoBasket;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Basket;
import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;
import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;
import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;
import ua.nure.khandzhian.SummaryTask4.sendEmail.SendTicket;
import ua.nure.khandzhian.SummaryTask4.validator.FieldsValidator;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class BuyTicket extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(BuyTicket.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.MAIN_PAGE;
		
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		DaoInterfaceBasket daoInterfaceBasket = new DaoBasket();
		DaoInterfaceFlights daoInterfaceFlight = new DaoFlights();
		DaoInterfaceOrders daoInterfaceOrders = new DaoOrders();
		
		String passportId = request.getParameter("passportId");
		LOGGER.debug("Request parameter: passportId --> " + passportId);
		
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		LOGGER.debug("Request parameter: flightId --> " + flightId);
		
		String cardNumber = request.getParameter("cardNumber");
		LOGGER.debug("Request parameter: flightId --> " + cardNumber);
		
		String date = request.getParameter("date");
		LOGGER.debug("Request parameter: flightId --> " + date);
		
		String CVV = request.getParameter("CVV");
		LOGGER.debug("Request parameter: flightId --> " + CVV);
		
		FieldsValidator validator = new FieldsValidator();
		if(!validator.cardNumber(cardNumber)) {
			throw new Exception("Invalid_card_number");
		}
		if(!validator.cardDate(date)) {
			throw new Exception("Invalid_date");
		}
		if(!validator.cardCVV(CVV)) {
			throw new Exception("Invalid_CVV");
		}
		
		Passengers passenger = daoInterfacePassengers.findPassengerById(passportId);
		Flights flight = daoInterfaceFlight.findFlightById(flightId);
		
		Basket basket = new Basket();
		basket.setId(flightId);
		basket.setPassportId(passportId);
		
		List<Basket> list = daoInterfaceBasket.getBasketByClientId(basket);
		Iterator<Basket> it = list.iterator();
				
		TicketCreator ticket = new TicketCreator();
		SendTicket send = new SendTicket();
		
		Iterator<Basket> it1 = list.iterator();
		String ticketForSend = "";
		while(it1.hasNext()){
			ticketForSend += it1.next().getTicketId();
			if(it1.hasNext()) {
				ticketForSend += ", ";
			}
		}
		
		Orders orderForTicket = new Orders();
		orderForTicket.setId(flightId);
		orderForTicket.setPassportId(passportId);

		ticket.createTicket(orderForTicket, ticketForSend);
		send.sendEmail(passenger.getEmail(), orderForTicket, ticketForSend);
		
		while(it.hasNext()){
			
			int ticketId = it.next().getTicketId();
	
			Orders order = new Orders();			
			order.setId(flightId);
			order.setPassportId(passportId);
			order.setFirstName(passenger.getFirstName());
			order.setSecondName(passenger.getSecondName());
			order.setEmail(passenger.getEmail());
			order.setNameOfFlight(flight.getNameOfFlight());
			order.setFlightNumber(flight.getFlightNumber());
			order.setDeparture(flight.getDeparture());
			order.setDepartureDateTime(flight.getDepartureDateTime());
			order.setDestination(flight.getDestination());
			order.setDestinationDateTime(flight.getDestinationDateTime());
			order.setPrice(flight.getPrice());
			order.setTicketId(ticketId);
			
			basket.setTicketId(ticketId);
			
			daoInterfaceOrders.insertOrders(order);
			
			daoInterfaceBasket.deleteFromBasket(basket);
		}
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
