package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.entity.Airplane;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class UpdateAirplane extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UpdateAirplane.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		String forward = Page.ADMINISTRATOR_AIRPLANE_PAGE;
		
		DaoInterfaceAirplanes daoInterfaceAirplanes = new DaoAirplanes();
		
		int airplaneId = Integer.parseInt(request.getParameter("airplaneId"));
		LOGGER.debug("Request parameter: airplaneId --> " + airplaneId);
		
		if(request.getParameter("airplaneId").isEmpty()) {
			throw new Exception("AirplaneID_can_not_empty");
		}
		
		String model = request.getParameter("model");
		LOGGER.debug("Request parameter: model --> " + model);
		
		if(model.isEmpty()) {
			throw new Exception("Model_can_not_empty");
		}
		
		int numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
		LOGGER.debug("Request parameter: numberOfSeats --> " + numberOfSeats);
		
		if(request.getParameter("numberOfSeats").isEmpty()) {
			throw new Exception("Number_of_seats_can_not_empty");
		}
		
		Airplane airplane = new Airplane();
		airplane.setIdAirplane(airplaneId);
		airplane.setModel(model);
		airplane.setNumberOfSeats(numberOfSeats);
		
		daoInterfaceAirplanes.updateAirplane(airplane);
		
		LOGGER.debug("Command finished");
		
		return forward;
	}

}
