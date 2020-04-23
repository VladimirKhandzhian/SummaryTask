package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class DeleteWorker extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DeleteWorker.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		
		int workerId = Integer.parseInt(request.getParameter("workerId"));
		LOGGER.debug("Request parameter: workerId --> " + workerId);
				
		String forward = Page.ADMINISTRATOR_WORKER_PAGE;
		
		daoInterfaceWorkers.deleteWorker(workerId);
			
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
