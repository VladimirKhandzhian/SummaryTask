package ua.nure.khandzhian.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;
import ua.nure.khandzhian.SummaryTask4.web.command.CommandContainer;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Controller.class);

    public Controller() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.trace("DoGet");
		process(request, response, "GET");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.trace("DoPost");
		process(request, response, "POST");
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response, String method) throws ServletException, IOException {
		BasicConfigurator.configure();
		
		String forward = Page.ERROR_PAGE;
		String commandName = request.getParameter("command");
		LOGGER.trace("Request parameter: command --> " + commandName);
		
		Command command = CommandContainer.get(commandName);
		LOGGER.trace("Obtained command --> " + command);
		try {
			forward = command.execute(request, response);
		} catch (Exception e) {
			if (e.getCause() != null) {
				request.setAttribute("errorMessage", e.getMessage() + ": "
						+ e.getCause().getMessage());
			} else {
				request.setAttribute("errorMessage", e.getMessage());
			}
		}
		if(method.contentEquals("GET") || forward.equals("WEB-INF/Error.jsp")) {
			request.getRequestDispatcher(forward).forward(request, response);
		}else {
			response.sendRedirect(forward);
		}
	}
}
