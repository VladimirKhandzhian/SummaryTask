package ua.nure.khandzhian.SummaryTask4.web.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class DeleteClient extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DeleteClient.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		
		String passportId = (String) session.getAttribute("passportId");
		LOGGER.debug("Request parameter: passportId --> " + passportId);
				
		String forward = Page.MAIN_PAGE;
		
		daoInterfacePassengers.deletePassenger(passportId);
			
		session.invalidate();
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
