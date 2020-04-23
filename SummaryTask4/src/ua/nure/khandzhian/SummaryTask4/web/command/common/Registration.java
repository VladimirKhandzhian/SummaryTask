package ua.nure.khandzhian.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfacePassengers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoPassengers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Passengers;
import ua.nure.khandzhian.SummaryTask4.validator.FieldsValidator;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;
import ua.nure.khandzhian.SummaryTask4.Encrypt.SHAEncrypt;

public class Registration extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Registration.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.MAIN_PAGE;
		
		HttpSession session = request.getSession();
		
		DaoInterfacePassengers daoInterfacePassengers = new DaoPassengers();
		FieldsValidator fieldsValidator = new FieldsValidator();
		
		String passportId = request.getParameter("passportNumber");
		LOGGER.debug("Request parameter: passportId --> " + passportId);
		
		if(passportId.isEmpty()) {
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
		LOGGER.debug("Request parameter: destination --> " + email);
		
		if(email.isEmpty()) {
			throw new Exception("Email_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldEmail(email)) {
			throw new Exception("Email_entered_incorrectly");
		}
		
		for(Passengers passenger : daoInterfacePassengers.getPassengers()){
		    if(passenger.getEmail().equals(email)) {
		    	throw new Exception("Please_enter_another_email._This_email_ already_exists");
		    }
		}
			
		String login = request.getParameter("login");
		LOGGER.debug("Request parameter: login --> " + login);
		
		if(login.isEmpty()) {
			throw new Exception("Login_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldLoginPassword(login)) {
			throw new Exception("Login_must_be_entered_on_english_without_spaces_and_not_exceed_20_characters");
		}
		
		for(Passengers passenger : daoInterfacePassengers.getPassengers()){
		    if(passenger.getLogin().equals(login)) {
		    	throw new Exception("Please_enter_another_Login");
		    }
		}
		
		String password = request.getParameter("password");
		LOGGER.debug("Request parameter: password --> " + password);
		
		if(password.isEmpty()) {
			throw new Exception("Password_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldLoginPassword(login)) {
			throw new Exception("Password_must_be_entered_on_english_without_spaces_and_not_exceed_20_characters");
		}
		
		String confirmPassword = request.getParameter("confirmPassword");
		LOGGER.debug("Request parameter: destination --> " + confirmPassword);
		
		if(!password.equals(confirmPassword)) {
			throw new Exception("Incorrect_confirm_password");
		}
		
		Passengers passenger = new Passengers();
		passenger.setPassportId(passportId);
		passenger.setFirstName(firstName);
		passenger.setSecondName(secondName);
		passenger.setEmail(email);
		passenger.setLogin(login);
		passenger.setPassword(SHAEncrypt.encryptPassword(password));
		
		daoInterfacePassengers.insertPassenger(passenger);
		
		session.setAttribute("passportId", passportId);
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
