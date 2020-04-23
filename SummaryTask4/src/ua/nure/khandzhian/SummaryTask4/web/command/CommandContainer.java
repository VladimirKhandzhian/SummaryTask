package ua.nure.khandzhian.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.khandzhian.SummaryTask4.web.command.admin.AddAirplane;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.AddFlight;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.AddWorker;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.DeleteAirplane;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.DeleteFlight;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.DeleteWorker;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.GoToForAdministrator;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.GoToFromMainPage;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.UpdateAirplane;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.UpdateFlight;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.UpdateStatusRequest;
import ua.nure.khandzhian.SummaryTask4.web.command.admin.UpdateWorker;
import ua.nure.khandzhian.SummaryTask4.web.command.client.AddToBasket;
import ua.nure.khandzhian.SummaryTask4.web.command.client.BuyTicket;
import ua.nure.khandzhian.SummaryTask4.web.command.client.DeleteClient;
import ua.nure.khandzhian.SummaryTask4.web.command.client.DeleteFromBasket;
import ua.nure.khandzhian.SummaryTask4.web.command.client.GoToCabinet;
import ua.nure.khandzhian.SummaryTask4.web.command.client.GoToTheChoiceOfPlace;
import ua.nure.khandzhian.SummaryTask4.web.command.client.GoToUpdateClient;
import ua.nure.khandzhian.SummaryTask4.web.command.client.UpdateClient;
import ua.nure.khandzhian.SummaryTask4.web.command.common.Language;
import ua.nure.khandzhian.SummaryTask4.web.command.common.LogOut;
import ua.nure.khandzhian.SummaryTask4.web.command.common.Login;
import ua.nure.khandzhian.SummaryTask4.web.command.common.Registration;
import ua.nure.khandzhian.SummaryTask4.web.command.common.SearchFlightByAddress;
import ua.nure.khandzhian.SummaryTask4.web.command.common.SearchFlightByNumber;
import ua.nure.khandzhian.SummaryTask4.web.command.common.Sort;
import ua.nure.khandzhian.SummaryTask4.web.command.dispatcher.AddToBrigad;
import ua.nure.khandzhian.SummaryTask4.web.command.dispatcher.DeleteFromBrigad;
import ua.nure.khandzhian.SummaryTask4.web.command.dispatcher.GoToPassengerList;
import ua.nure.khandzhian.SummaryTask4.web.command.dispatcher.SendRequest;
import ua.nure.khandzhian.SummaryTask4.web.command.dispatcher.UpdateStatusFlight;

public class CommandContainer {
	
	private static final Logger LOGGER = Logger.getLogger(CommandContainer.class);
	private static Map<String, Command> command = new TreeMap<String, Command>();
	
	static {
		
		//Common
		command.put("login", new Login());
		command.put("registration", new Registration());
		command.put("searchFlightByNumber", new SearchFlightByNumber());
		command.put("searchFlightByAddress", new SearchFlightByAddress());
		command.put("sort", new Sort());
		command.put("goToPassengerList", new GoToPassengerList());
		command.put("logOut", new LogOut());
		command.put("language", new Language());
		
		//Client
		command.put("goToCabinet", new GoToCabinet());
		command.put("goToUpdateClient", new GoToUpdateClient());
		command.put("updateClient", new UpdateClient());
		command.put("deleteClient", new DeleteClient());
		command.put("goToTheChoiceOfPlace", new GoToTheChoiceOfPlace());
		command.put("deleteFromBasket", new DeleteFromBasket());
		command.put("addToBasket", new AddToBasket());
		command.put("buyTicket", new BuyTicket());
		
		//Dispatcher
		command.put("createBrigad", new AddToBrigad());
		command.put("sendRequest", new SendRequest());
		command.put("updateStatusFlights", new UpdateStatusFlight());
		command.put("deleteFromBrigad", new DeleteFromBrigad());
		command.put("addToBrigad", new AddToBrigad());
		
		//Administrator
		command.put("addAirplane", new AddAirplane());
		command.put("addFlight", new AddFlight());
		command.put("addWorker", new AddWorker());
		command.put("goToForAdministrator", new GoToForAdministrator());
		command.put("deleteAirplane", new DeleteAirplane());
		command.put("deleteFlight", new DeleteFlight());
		command.put("updateStatusRequest", new UpdateStatusRequest());
		command.put("updateAirplane", new UpdateAirplane());
		command.put("updateFlight", new UpdateFlight());
		command.put("updateWorker", new UpdateWorker());
		command.put("deleteWorker", new DeleteWorker());
		command.put("goToFromMainPage", new GoToFromMainPage());	

		
		LOGGER.debug("Command container was successfully initialized");
		LOGGER.trace("Number of commands --> " + command.size());
	}
	
	public static Command get(String commandName) {
		return command.get(commandName);
	}
}

