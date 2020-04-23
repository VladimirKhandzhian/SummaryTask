package ua.nure.khandzhian.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.Page;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceBrigads;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceWorkers;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoAirplanes;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoBrigads;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights;
import ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoWorkers;
import ua.nure.khandzhian.SummaryTask4.db.entity.Flights;
import ua.nure.khandzhian.SummaryTask4.web.command.Command;

public class GoToForAdministrator extends Command{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GoToForAdministrator.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOGGER.debug("Command starts");
		
		DaoInterfaceFlights daoInterfaceFlights = new DaoFlights();
		DaoInterfaceAirplanes daoInterfaceAirplanes = new DaoAirplanes();
		DaoInterfaceBrigads daoInterfaceBrigads = new DaoBrigads();
		DaoInterfaceWorkers daoInterfaceWorkers = new DaoWorkers();
		
		String page = request.getParameter("page");
		LOGGER.debug("Get parameter: page --> " + page);
		
		if("UpdateFlight.jsp".equals(page)) {
			
			String forward = Page.GO_TO_FOR_ADMINISTRATOR + page;
			
			int flightId = Integer.parseInt(request.getParameter("flightId"));
			LOGGER.debug("Request parameter: flightId --> " + flightId);
			
			Flights flight = daoInterfaceFlights.findFlightById(flightId);
			
			request.setAttribute("flight", flight);
			LOGGER.debug("Set attribute: flight --> " + flight);
			
			request.setAttribute("model", daoInterfaceAirplanes.getAirplaneModelById(flight.getIdAirplane()));
			LOGGER.debug("Set attribute: model --> " + daoInterfaceAirplanes.getAirplaneModelById(flight.getIdAirplane()));
			
			request.setAttribute("numberOfSeats", daoInterfaceAirplanes.getNumberOfSeatsById(flight.getIdAirplane()));
			LOGGER.debug("Set attribute: numberOfSeats --> " + daoInterfaceAirplanes.getNumberOfSeatsById(flight.getIdAirplane()));
			
			request.setAttribute("emptyModel", daoInterfaceAirplanes.getEmptyAirplane(flight.getIdAirplane()));
			LOGGER.debug("Set attribute: emptyModel --> " + daoInterfaceAirplanes.getEmptyAirplane(flight.getIdAirplane()));
			
			request.setAttribute("emptyBrigad", daoInterfaceBrigads.getEmptyBrigads(flight.getIdBrigad()));
			LOGGER.debug("Set attribute: emptyBrigad --> " + daoInterfaceBrigads.getEmptyBrigads(flight.getIdBrigad()));
			
			LOGGER.debug("Command finished");
			
			return forward;
			
		}else if("AddFlight.jsp".equals(page)) {
			
			String forward = Page.GO_TO_FOR_ADMINISTRATOR + page;
						
			request.setAttribute("airplanes", daoInterfaceAirplanes.getAirplane());
			LOGGER.debug("Set attribute: airplanes --> " + daoInterfaceAirplanes.getAirplane());
			
			request.setAttribute("brigads", daoInterfaceBrigads.getDistinctBrigads());
			LOGGER.debug("Set attribute: brigads --> " + daoInterfaceBrigads.getDistinctBrigads());
			
			LOGGER.debug("Command finished");
			
			return forward;
			
		}else if("UpdateWorker.jsp".equals(page)) {
			
			String forward = Page.GO_TO_FOR_ADMINISTRATOR + page;
			
			int workerId = Integer.parseInt(request.getParameter("workerId"));
			LOGGER.debug("Request parameter: workerId --> " + workerId);
			
			request.setAttribute("worker", daoInterfaceWorkers.findWorkerById(workerId));
			LOGGER.debug("Set attribute: worker --> " + daoInterfaceWorkers.findWorkerById(workerId));
			
			LOGGER.debug("Command finished");
			
			return forward;
			
		}else if("UpdateAirplane.jsp".equals(page)) {
			
			String forward = Page.GO_TO_FOR_ADMINISTRATOR + page;
			
			int airplaneId = Integer.parseInt(request.getParameter("airplaneId"));
			LOGGER.debug("Request parameter: airplaneId --> " + airplaneId);
			
			request.setAttribute("airplane", daoInterfaceAirplanes.findAirplaneById(airplaneId));
			LOGGER.debug("Set attribute: airplane --> " + daoInterfaceAirplanes.findAirplaneById(airplaneId));
			
			LOGGER.debug("Command finished");
			
			return forward;
			
		}else{
			
			String forward = Page.GO_TO_FOR_ADMINISTRATOR + page;
			
			LOGGER.debug("Command finished");
			
			return forward;
			
		}
	}
}
