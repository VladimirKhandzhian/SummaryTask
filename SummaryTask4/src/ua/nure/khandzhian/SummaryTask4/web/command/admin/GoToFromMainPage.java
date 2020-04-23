package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Workers;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class GoToFromMainPage extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GoToFromMainPage.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("workerId") != null) {
			
			int workerId = (int) session.getAttribute("workerId");				
			Workers worker = daoInterfaceWorkers.findWorkerById(workerId);	
			String forward;
				
			if (worker.getIdStatus() == 1) {
					
				forward = Page.WORKER_PAGE;
					
				LOGGER.debug("Command finished");
					
				return forward;
					
			}
				
			if(worker.getIdStatus() == 2) {
					
				forward = Page.DISPATCHER_PAGE;
					
				LOGGER.debug("Command finished");
					
				return forward;
					
			}
				
		} else {
				
			String forward = Page.ADMINISTRATOR_FLIGHT_PAGE;
			
			LOGGER.debug("Command finished");
				
			return forward;
				
		}
		return Page.ERROR_PAGE;
	}
}
