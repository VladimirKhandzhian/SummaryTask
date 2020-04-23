package ua.nure.khandzhian.SummaryTask4.web.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;
import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class GoToCabinet extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GoToCabinet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOGGER.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		DaoInterfaceOrders daoInterfaceOrders = new DaoOrders();
		
		String forward = Page.CLIENT_PAGE;
		
		String passportId = (String) session.getAttribute("passportId");
		LOGGER.debug("Get attribute passportId --> " + passportId);
		
		Passengers passenger = daoInterfacePassengers.findPassengerById(passportId);
		
		Orders order = new Orders();
		order.setPassportId(passportId);
		
		request.setAttribute("passenger", passenger);
		LOGGER.debug("Set attribute passenger --> " + passenger);
		
		request.setAttribute("orders", daoInterfaceOrders.getOrdersByPassportId(order));
		LOGGER.debug("Set attribute orders --> " + daoInterfaceOrders.getOrdersByPassportId(order));
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
