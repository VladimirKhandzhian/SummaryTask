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

public class UpdateWorker extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UpdateWorker.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.ADMINISTRATOR_WORKER_PAGE;
		
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		FieldsValidator fieldsValidator = new FieldsValidator();
		
		int workerId = Integer.parseInt(request.getParameter("workerId"));
		LOGGER.debug("Request parameter: workerId --> " + workerId);
		
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
		LOGGER.debug("Request parameter: email --> " + email);
		
		if(email.isEmpty()) {
			throw new Exception("Email_can_not_empty");
		}
		
		if(!fieldsValidator.validateFieldEmail(email)) {
			throw new Exception("Email_entered_incorrectly");
		}
		
		Workers worker = new Workers();
		worker.setIdWorker(workerId);
		worker.setFirstName(firstName);
		worker.setSecondName(secondName);
		worker.setPhoneNumber(phoneNumber);
		worker.setEmail(email);
		worker.setProfession(profession);
		
		daoInterfaceWorkers.updateWorker(worker);

		LOGGER.debug("Command finished");
		
		return forward;
	}

}
