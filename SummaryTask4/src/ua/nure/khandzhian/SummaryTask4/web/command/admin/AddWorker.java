package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Workers;
import ua.nure.khandzhian.SummaryTask4.validator.FieldsValidator;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;
import ua.nure.khandzhian.SummaryTask4.Encrypt.SHAEncrypt;

public class AddWorker extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(AddWorker.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.ADMINISTRATOR_WORKER_PAGE;
		
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		FieldsValidator fieldsValidator = new FieldsValidator();
		
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
		
		String profession = request.getParameter("profession");
		LOGGER.debug("Request parameter: profession --> " + profession);
		
		if(profession.isEmpty()) {
			throw new Exception("Profession_can_not_empty");
		}
		
		String phoneNumber = request.getParameter("phoneNumber");
		LOGGER.debug("Request parameter: phoneNumber --> " + phoneNumber);
		
		if(phoneNumber.isEmpty()) {
			throw new Exception("Phone_number_can_not_empty");
		}
		
		String email = request.getParameter("email");
		LOGGER.debug("Request parameter: destination --> " + email);
		
		if(email.isEmpty()) {
			throw new Exception("Email_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldEmail(email)) {
			throw new Exception("Email_entered_incorrectly");
		}
		
		String status = request.getParameter("status");
		LOGGER.debug("Request parameter: status --> " + status);
		
		int statusId = 1;
		switch(status) {
			case "Сотрудник": statusId = 1;
				break;
			case "Диспетчер": statusId = 2;
				break;
			case "Администратор": statusId = 3;
				break;
		}
			
		String login = request.getParameter("login");
		LOGGER.debug("Request parameter: login --> " + login);
		
		if(login.isEmpty()) {
			throw new Exception("Login_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldLoginPassword(login)) {
			throw new Exception("Login_must_be_entered_in_one_language_without_spaces_and_not_exceed_20_characters");
		}
		
		for (Workers worker : daoInterfaceWorkers.getWorkers()){
		    if(worker.getLogin().equals(login)) {
		    	throw new Exception("Please_enter_another_Login");
		    }
		}
		
		String password = request.getParameter("password");
		LOGGER.debug("Request parameter: password --> " + password);
		
		if(password.isEmpty()) {
			throw new Exception("Password_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldLoginPassword(login)) {
			throw new Exception("Password_must_be_entered_in_one_language_without_spaces_and_not_exceed_20_characters");
		}
		
		String confirmPassword = request.getParameter("confirmPassword");
		LOGGER.debug("Request parameter: destination --> " + confirmPassword);
		
		if(!password.equals(confirmPassword)) {
			new Exception("Incorrect confirm password");
		}
		
		Workers worker = new Workers();
		worker.setFirstName(firstName);
		worker.setSecondName(secondName);
		worker.setPhoneNumber(phoneNumber);
		worker.setEmail(email);
		worker.setProfession(profession);
		worker.setLogin(login);
		worker.setPassword(SHAEncrypt.encryptPassword(password));
		worker.setIdStatus(statusId);
		
		daoInterfaceWorkers.insertWorker(worker);
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
