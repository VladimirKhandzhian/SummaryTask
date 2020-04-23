package ua.nure.khandzhian.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class SearchFlightByNumber extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(SearchFlightByNumber.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.SEARCH_PAGE;
		
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		
		String flightNumber = request.getParameter("flightNumber");
		LOGGER.debug("Request parameter: flightNumber --> " + flightNumber);
		
		request.setAttribute("fly", daoInterfaceFlights.getFlightsByNumber(flightNumber));
		LOGGER.debug("Set attribute: fly --> " + daoInterfaceFlights.getFlightsByNumber(flightNumber));
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
