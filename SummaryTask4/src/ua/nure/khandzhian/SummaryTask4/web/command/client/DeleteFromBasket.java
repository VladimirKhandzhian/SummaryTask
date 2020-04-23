package ua.nure.khandzhian.SummaryTask4.web.command.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBasket;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoBasket;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Airplane;
import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;
import ua.nure.khandzhian.SummaryTask4.db.entity.Basket;

public class DeleteFromBasket extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DeleteFromBasket.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceFlights daoInterfaceFlight = new DaoFlights();
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		DaoInterfaceAirplanes daoInterfaceAirplanes = new DaoAirplanes();
		DaoInterfaceBasket daoIntefaceBasket = new DaoBasket();
		
		HttpSession session = request.getSession();
		
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		LOGGER.debug("Request parameter: flightId --> " + flightId);
		
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		LOGGER.debug("Request parameter: ticketId --> " + ticketId);
		
		String passportId = (String) session.getAttribute("passportId");
		LOGGER.debug("Request parameter: passportId --> " + passportId);
			
		String forward = Page.CLIENT_CHOICE_OF_PLACE;
		
		Flights flight = daoInterfaceFlight.findFlightById(flightId);
		
		int airplaneId = flight.getIdAirplane();
		
		Airplane airplane = daoInterfaceAirplanes.findAirplaneById(airplaneId);
		
		List<Integer> place = new ArrayList<Integer>();
		
		for(int i = 0; i < airplane.getNumberOfSeats(); i++) {
			place.add(i + 1);
		}
		
		Basket basket = new Basket();
		basket.setId(flightId);
		basket.setTicketId(ticketId);
		basket.setPassportId(passportId);
		
		daoIntefaceBasket.deleteFromBasket(basket);
		
		request.setAttribute("flightId", flightId);
		LOGGER.debug("Set attribute: flightId --> " + flightId);
		
		request.setAttribute("places", place);
		LOGGER.debug("Set attribute: places --> " + place);
		
		request.setAttribute("passenger", daoInterfacePassengers.findPassengerById(passportId));
		LOGGER.debug("Set attribute: passenger --> " + daoInterfacePassengers.findPassengerById(passportId));
		
		request.setAttribute("occupiedPlaces", daoIntefaceBasket.getTicketByFlightId(flightId));
		LOGGER.debug("Set attribute: occupiedPlaces --> " + daoIntefaceBasket.getTicketByFlightId(flightId));
		
		request.setAttribute("occupiedThisClientPlaces", daoIntefaceBasket.getBasketByClientId(basket));
		LOGGER.debug("Set attribute: occupiedThisClientPlaces --> " + daoIntefaceBasket.getBasketByClientId(basket));
		
		request.setAttribute("sumToPaid", daoIntefaceBasket.getSumToPaid(basket));
		LOGGER.debug("Set attribute: sumToPaid --> " + daoIntefaceBasket.getSumToPaid(basket));

		LOGGER.debug("Command finished");
		
		return forward;
	}

}
