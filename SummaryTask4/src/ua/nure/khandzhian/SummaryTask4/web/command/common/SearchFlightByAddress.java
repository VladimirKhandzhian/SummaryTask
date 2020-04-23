package ua.nure.khandzhian.SummaryTask4.web.command.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class SearchFlightByAddress extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(SearchFlightByAddress.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.SEARCH_PAGE;
		
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		
		if(request.getParameter("departure").isEmpty()) {
			throw new Exception("Field_departure_can_not_be_empty");
		}
		
		String departure = request.getParameter("departure");
		LOGGER.debug("Request parameter: departure --> " + departure);
		
		if(request.getParameter("destination").isEmpty()) {
			throw new Exception("Field_destination_can_not_be_empty");
		}
		
		String destination = request.getParameter("destination");
		LOGGER.debug("Request parameter: destination --> " + destination);
		
		if(request.getParameter("departureDate").isEmpty()) {
			throw new Exception("Field_departureDate_can_not_be_empty");
		}
		
		LocalDate departureDate = LocalDate.parse(request.getParameter("departureDate"));
		LOGGER.debug("Request parameter: departureDate --> " + departureDate);
		
		
		
		LocalDateTime departureDateTime = departureDate.atStartOfDay();
		
		Flights flight = new Flights();
		
		flight.setDeparture(departure);
		flight.setDestination(destination);
		flight.setDepartureDateTime(departureDateTime);
		
		request.setAttribute("fly", daoInterfaceFlights.getFlightsByDirection(flight));
		LOGGER.debug("Set attribute: fly --> " + daoInterfaceFlights.getFlightsByDirection(flight));

		LOGGER.debug("Command finished");
		
		return forward;
	}

}
