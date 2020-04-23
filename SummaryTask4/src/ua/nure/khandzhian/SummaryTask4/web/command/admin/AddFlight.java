package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class AddFlight extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(AddFlight.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.ERROR_PAGE;
		
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		
		String flightNumber = request.getParameter("flightNumber");
		LOGGER.debug("Request parameter: flightNumber --> " + flightNumber);
		
		if(flightNumber.isEmpty()) {
			throw new Exception("Flight_number_can_not_empty");
		}
		
		String nameOfFlight = request.getParameter("nameOfFlight");
		LOGGER.debug("Request parameter: nameOfFlight --> " + nameOfFlight);
		
		if(nameOfFlight.isEmpty()) {
			throw new Exception("Name_Of_Flight_can_not_empty");
		}
		
		String departure = request.getParameter("departure");
		LOGGER.debug("Request parameter: departure --> " + departure);
		
		if(departure.isEmpty()) {
			throw new Exception("Departure_can_not_empty");
		}
		
		String destination = request.getParameter("destination");
		LOGGER.debug("Request parameter: destination --> " + destination);
		
		if(destination.isEmpty()) {
			throw new Exception("Destination_can_not_empty");
		}
		
		if(request.getParameter("departureDateTime").isEmpty()) {
			throw new Exception("Departure_date_and_time_can_not_empty");
		}
		
		LocalDateTime departureDateTime = LocalDateTime.parse(request.getParameter("departureDateTime"));
		LOGGER.debug("Request parameter: departureDateTime --> " + departureDateTime);
		
		if(request.getParameter("destinationDateTime").isEmpty()) {
			throw new Exception("Destination_date_and_time_can_not_empty");
		}
		
		LocalDateTime destinationDateTime = LocalDateTime.parse(request.getParameter("destinationDateTime"));
		LOGGER.debug("Request parameter: destinationDateTime --> " + destinationDateTime);
		
		int airplaneId = Integer.parseInt(request.getParameter("airplaneId"));
		LOGGER.debug("Request parameter: airplaneId --> " + airplaneId);
		
		int brigadId = Integer.parseInt(request.getParameter("brigadId"));
		LOGGER.debug("Request parameter: brigadId --> " + brigadId);
		
		double price = Double.parseDouble(request.getParameter("price"));
		LOGGER.debug("Request parameter: price --> " + price);
		
		if(request.getParameter("price").isEmpty()) {
			throw new Exception("Price_can_not_empty");
		}
		
		Flights flights = new Flights();
		flights.setFlightNumber(flightNumber);
		flights.setNameOfFlight(nameOfFlight);
		flights.setDeparture(departure);
		flights.setDestination(destination);
		flights.setDepartureDateTime(departureDateTime);
		flights.setDestinationDateTime(destinationDateTime);
		flights.setIdAirplane(airplaneId);
		flights.setIdBrigad(brigadId);
		flights.setPrice(price);
		
		daoInterfaceFlights.insertFlight(flights);

		LOGGER.debug("Command finished");
		
		forward = Page.ADMINISTRATOR_FLIGHT_PAGE;
		
		return forward;
	}

}
