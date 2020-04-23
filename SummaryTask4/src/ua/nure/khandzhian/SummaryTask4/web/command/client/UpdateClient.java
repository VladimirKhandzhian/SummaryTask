package ua.nure.khandzhian.SummaryTask4.web.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoOrders;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;
import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;
import ua.nure.khandzhian.SummaryTask4.validator.FieldsValidator;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class UpdateClient extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UpdateClient.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.CLIENT_CABINET_PAGE;
		
		HttpSession session = request.getSession();
		
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		DaoInterfaceOrders daoInterfaceOrders = new DaoOrders();
		
		FieldsValidator fieldsValidator = new FieldsValidator();
		
		String newPassportId = request.getParameter("newPassportId");
		LOGGER.debug("Request parameter: newPassportId --> " + newPassportId);
		
		if(newPassportId.isEmpty()) {
			throw new Exception("PassportID_can_not_empty");
		}
		
		String oldPassportId = (String) session.getAttribute("passportId");
		LOGGER.debug("Request parameter: oldPassportId --> " + oldPassportId);
		
		if(oldPassportId.isEmpty()) {
			throw new Exception("PassportID_can_not_empty");
		}
		
		String firstName = request.getParameter("firstName");
		LOGGER.debug("Request parameter: firstName --> " + firstName);
		
		if(firstName.isEmpty()) {
			throw new Exception("First_name_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldName(firstName)) {
			throw new Exception("First_name_must_be_entered_in_one_language_without_spaces_and_not_exceed_20_characters");
		}
		
		String secondName = request.getParameter("secondName");
		LOGGER.debug("Request parameter: secondName --> " + secondName);
		
		if(secondName.isEmpty()) {
			throw new Exception("Second_name_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldName(secondName)) {
			throw new Exception("Second_name_must_be_entered_in_one_language_without_spaces_and_not_exceed_20_characters");
		}
		
		String email = request.getParameter("email");
		LOGGER.debug("Request parameter: email --> " + email);
		
		if(email.isEmpty()) {
			throw new Exception("Email_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldEmail(email)) {
			throw new Exception("Email_entered_incorrectly");
		}
		
		Passengers passenger = new Passengers();
		passenger.setPassportId(newPassportId);
		passenger.setFirstName(firstName);
		passenger.setSecondName(secondName);
		passenger.setEmail(email);
		
		Orders order = new Orders();
		order.setPassportId(newPassportId);
		
		daoInterfacePassengers.updatePassenger(passenger, oldPassportId);
		
		session.setAttribute("passportId", newPassportId);
		LOGGER.debug("Set attribute passportId --> " + newPassportId);
		
		request.setAttribute("passenger", passenger);
		LOGGER.debug("Set attribute passenger --> " + passenger);
		
		request.setAttribute("orders", daoInterfaceOrders.getOrdersByPassportId(order));
		LOGGER.debug("Set attribute orders --> " + daoInterfaceOrders.getOrdersByPassportId(order));
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
