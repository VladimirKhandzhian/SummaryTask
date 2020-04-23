package ua.nure.khandzhian.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class LogOut extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(LogOut.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.MAIN_PAGE;
		
		HttpSession session = request.getSession(false);
		
		session.invalidate();
		
		LOGGER.debug("Command finished");

		return forward;
	}

}
