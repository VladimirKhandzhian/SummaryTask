package ua.nure.khandzhian.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Workers;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;
import ua.nure.khandzhian.SummaryTask4.Encrypt.SHAEncrypt;

public class Login extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Login.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceWorkers daoInterfaceWorker = new DaoWorkers();
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		
		HttpSession session = request.getSession();
		
		String login = request.getParameter("login");
		LOGGER.debug("Request parameter: login --> " + login);
		
		String password = request.getParameter("password");
		LOGGER.debug("Request parameter: password --> " + password);
		
		if (login.isEmpty() || password.isEmpty()) {
			throw new Exception("Login/password_can_not_be_empty");
		}
		
		Workers worker = daoInterfaceWorker.findWorkerByLogin(login);
		Passengers passenger = daoInterfacePassengers.findPassengerByLogin(login);
		
		if ((worker == null || !SHAEncrypt.encryptPassword(password).equals(worker.getPassword())) &&
				(passenger == null || !SHAEncrypt.encryptPassword(password).equals(passenger.getPassword()))) {
			throw new Exception("Can_not_find_user_with_such_login/password");
		}
		
		int workerId = 0;
		String passportId = "";
		
		if(worker != null) {
			workerId = worker.getIdWorker();
		}else {
			passportId = passenger.getPassportId();
		}
		String forward = Page.MAIN_PAGE;

		if(passenger != null) {
			
			session.setAttribute("passportId", passportId);
			LOGGER.debug("Set attribute passportId --> " + passportId);
			
			LOGGER.debug("Command finished");
			
			return forward;
				
		}
		
		if(worker != null) {
			if(worker.getIdStatus() == 1) {
			
				session.setAttribute("workerId", workerId);
				LOGGER.debug("Set attribute workerId --> " + workerId);
			
				forward = Page.WORKER_CABINET_PAGE;
			
				LOGGER.debug("Command finished");
			
				return forward;
			
			}else if(worker.getIdStatus() == 2) {
						
				session.setAttribute("workerId", workerId);
				LOGGER.debug("Set session workerId --> " + workerId);
			
				forward = Page.DISPATCHER_CABINET_PAGE;
			
				LOGGER.debug("Command finished");
			
				return forward;
			
			}else {
			
				session.setAttribute("administratorId", workerId);
				LOGGER.debug("Set session administratorId --> " + workerId);
			
				forward = Page.ADMINISTRATOR_FLIGHT_PAGE;
			
				LOGGER.debug("Command finished");
			
				return forward;
			}
		}
		return forward;
	}

}
