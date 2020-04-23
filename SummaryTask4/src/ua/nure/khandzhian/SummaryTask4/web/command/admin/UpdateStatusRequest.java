package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceRequests;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoRequests;
import ua.nure.khandzhian.SummaryTask4.db.entity.Requests;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class UpdateStatusRequest extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UpdateStatusRequest.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.ADMINISTRATOR_REQUEST_PAGE;
	
		DaoInterfaceRequests daoInterfaceRequests = new DaoRequests();
		
		String responses = request.getParameter("status");
		LOGGER.debug("Request parameter: responses --> " + responses);
		
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		LOGGER.debug("Request parameter: secondName --> " + requestId);
		
		Requests requests = new Requests();
		requests.setIdRequest(requestId);
		requests.setResponse(responses);
		
		daoInterfaceRequests.updateRequest(requests);
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
