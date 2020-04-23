package ua.nure.khandzhian.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class Sort extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Sort.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.SORT_PAGE;
		
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		
		String typeOfSort = request.getParameter("typeOfSort");
		LOGGER.debug("Request parameter: typeOfSort --> " + typeOfSort);
		
		switch(typeOfSort) {
			case ("idByAsk"):
				request.setAttribute("fly", daoInterfaceFlights.getFlightsByIdAsc());
				break;
			case ("idByDesc"):
				request.setAttribute("fly", daoInterfaceFlights.getFlightsByIdDesc());
				break;
			case ("nameByAsk"):
				request.setAttribute("fly", daoInterfaceFlights.getFlightsByNameAsc());
				break;
			case ("nameByDesc"):
				request.setAttribute("fly", daoInterfaceFlights.getFlightsByNameDesc());
				break;
		}

		LOGGER.debug("Command finished");
		
		return forward;
	}

}
