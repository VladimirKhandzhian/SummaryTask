package ua.nure.khandzhian.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;
import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Workers;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class Language extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Language.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = request.getParameter("page");
		
		HttpSession session = request.getSession();
		
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		DaoInterfaceOrders daoInterfaceOrders = new DaoOrders();
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		
		if(forward.equals("Client.jsp")) {
			
			String passportId = (String) session.getAttribute("passportId");
			LOGGER.debug("Get attribute passportId --> " + passportId);
			
			Passengers passenger = daoInterfacePassengers.findPassengerById(passportId);
			
			Orders order = new Orders();
			order.setPassportId(passportId);
			
			request.setAttribute("passenger", passenger);
			LOGGER.debug("Set attribute passenger --> " + passenger);
			
			request.setAttribute("orders", daoInterfaceOrders.getOrdersByPassportId(order));
			LOGGER.debug("Set attribute orders --> " + daoInterfaceOrders.getOrdersByPassportId(order));
			
			forward = Page.CLIENT_PAGE;
			
		}else if(session.getAttribute("administratorId") != null) {
			
			forward = Page.GO_TO_FOR_ADMINISTRATOR + forward;
			
		}else if(session.getAttribute("workerId") != null) {
			
			int workerId = (int) session.getAttribute("workerId");
			LOGGER.debug("Session parameter: workerId --> " + workerId);
			
			Workers worker = daoInterfaceWorkers.findWorkerById(workerId);
			
			if(worker.getIdStatus() == 2) {
				
				if(forward.equals("MainPage.jsp")) {
					forward = Page.MAIN_PAGE;
				}else {
					forward = Page.DISPATCHER_PAGE;
				}
				
			}else {
				
				if(forward.equals("MainPage.jsp")) {
					forward = Page.MAIN_PAGE;
				}else {
					forward = Page.WORKER_PAGE;
				}
			}
		}	
		
		String language = request.getParameter("language");
		LOGGER.debug("Request parameter: language --> " + language);
		
		if(language.equals("ru")) {
			session.setAttribute("language", "ru");
		}else {
			session.setAttribute("language", "en");
		}
		LOGGER.debug("Set session attribute: language --> " + language);
		
		LOGGER.debug("Command finished");
		return forward;
	}

}
