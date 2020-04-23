package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;
public class DeleteFlight extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DeleteFlight.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		LOGGER.debug("Request parameter: flightId --> " + flightId);
		
			
		String forward = Page.ADMINISTRATOR_FLIGHT_PAGE;
		
		daoInterfaceFlights.deleteFlight(flightId);
			
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
