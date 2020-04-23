package ua.nure.khandzhian.SummaryTask4.web.command.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBrigads;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceRequests;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoBrigads;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoRequests;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class DeleteFromBrigad extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DeleteFromBrigad.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceWorkers daoInterfaceWorker = new DaoWorkers();
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		DaoInterfaceBrigads daoInterfaceBrigads = new DaoBrigads();
		DaoInterfaceRequests daoInterfaceRequests = new DaoRequests();
		
		HttpSession session = request.getSession();
		
		int brigadId = Integer.parseInt(request.getParameter("brigadId"));
		LOGGER.debug("Request parameter: brigadId --> " + brigadId);
		
		int workerId = Integer.parseInt(request.getParameter("workerId"));
		LOGGER.debug("Request parameter: workerId --> " + workerId);
		
		int dispatcherId = (int) session.getAttribute("workerId");
		LOGGER.debug("Request parameter: dispatcherId --> " + dispatcherId);
			
		String forward = Page.DISPATCHER_CABINET_PAGE;
		
		daoInterfaceBrigads.deleteWorkerFromBrigads(brigadId, workerId);
		
		request.setAttribute("flights", daoInterfaceFlights.getFlights());
		LOGGER.debug("Set attribute flights --> " + daoInterfaceFlights.getFlights());
			
		request.setAttribute("workers", daoInterfaceWorker.getWorkers());
		LOGGER.debug("Set attribute workers --> " + daoInterfaceWorker.getWorkers());
		
		request.setAttribute("brigads", daoInterfaceBrigads.getBrigads());
		LOGGER.debug("Set attribute brigads --> " + daoInterfaceBrigads.getBrigads());
		
		request.setAttribute("brigadsNumber", daoInterfaceBrigads.getDistinctBrigads());
		LOGGER.debug("Set attribute brigadsNumber --> " + daoInterfaceBrigads.getDistinctBrigads());
		
		request.setAttribute("emptyWorker", daoInterfaceWorker.getEmptyWorker());
		LOGGER.debug("Set attribute emptyWorker --> " + daoInterfaceWorker.getEmptyWorker());
		
		request.setAttribute("emptyCommander", daoInterfaceWorker.getEmptyCommander());
		LOGGER.debug("Set attribute emptyCommander --> " + daoInterfaceWorker.getEmptyCommander());
		
		request.setAttribute("requests", daoInterfaceRequests.getRequestsByWorkerId(dispatcherId));
		LOGGER.debug("Set attribute requests --> " + daoInterfaceRequests.getRequestsByWorkerId(dispatcherId));
			
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
