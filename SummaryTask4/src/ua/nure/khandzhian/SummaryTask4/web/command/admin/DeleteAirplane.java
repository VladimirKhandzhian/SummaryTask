package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoAirplanes;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class DeleteAirplane extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DeleteAirplane.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceAirplanes daoInterfaceAirplanes = new DaoAirplanes();
		
		int airplaneId = Integer.parseInt(request.getParameter("airplaneId"));
		LOGGER.debug("Request parameter: airplaneId --> " + airplaneId);
		
		String forward = Page.ADMINISTRATOR_AIRPLANE_PAGE;
		
		daoInterfaceAirplanes.deleteAirplane(airplaneId);
					
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
