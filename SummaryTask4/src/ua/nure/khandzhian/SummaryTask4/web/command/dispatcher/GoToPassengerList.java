package ua.nure.khandzhian.SummaryTask4.web.command.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoOrders;
import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class GoToPassengerList extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GoToPassengerList.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOGGER.debug("Command starts");
		
		DaoInterfaceOrders daoInterfaceOrders = new DaoOrders();
		
		String forward = Page.PASSENGER_LIST_PAGE;
		
		int flightId = Integer.parseInt(request.getParameter("flightId"));
		
		Orders order = new Orders();
		order.setId(flightId);
		
		request.setAttribute("passengerList", daoInterfaceOrders.getPassengersByFlightId(order));
		LOGGER.debug("Set attribute: passengerList --> " + daoInterfaceOrders.getPassengersByFlightId(order));
		
		request.setAttribute("flightId", flightId);
		LOGGER.debug("Set attribute: flightId --> " + flightId);
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
