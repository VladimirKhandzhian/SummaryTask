package ua.nure.khandzhian.SummaryTask4;

public class Page {
	
	//Common
	public static final String MAIN_PAGE = "MainPage.jsp";
	
	public static final String ERROR_PAGE = "WEB-INF/Error.jsp";
	
	public static final String SEARCH_PAGE = "WEB-INF/jsp/common/Search.jsp";
	
	public static final String SORT_PAGE = "WEB-INF/jsp/common/Sort.jsp";
	
	
	//Client
	public static final String CLIENT_PAGE = "WEB-INF/jsp/client/Client.jsp";
	
	public static final String CLIENT_CABINET_PAGE = "controller?command=goToCabinet&page=Client.jsp";
	
	public static final String CLIENT_UPDATE_PAGE = "WEB-INF/jsp/client/UpdateClient.jsp";
	
	public static final String CLIENT_CHOICE_OF_PLACE = "WEB-INF/jsp/client/ChoicePlace.jsp";
	

	//Worker
	public static final String WORKER_PAGE = "WEB-INF/jsp/worker/Worker.jsp";
	
	public static final String WORKER_CABINET_PAGE = "controller?command=goToFromMainPage";
	
	
	//Dispatcher
	public static final String DISPATCHER_PAGE = "WEB-INF/jsp/dispatcher/Dispatcher.jsp";
	
	public static final String DISPATCHER_CABINET_PAGE = "controller?command=goToFromMainPage";
	
	public static final String PASSENGER_LIST_PAGE = "WEB-INF/jsp/dispatcher/PassengerList.jsp";
	
	
	//Administrator
	public static final String ADMINISTRATOR_FLIGHT_PAGE = "controller?command=goToForAdministrator&page=Flight.jsp";
	
	public static final String ADMINISTRATOR_WORKER_PAGE = "controller?command=goToForAdministrator&page=Worker.jsp";
	
	public static final String ADMINISTRATOR_AIRPLANE_PAGE = "controller?command=goToForAdministrator&page=Airplane.jsp";
	
	public static final String ADMINISTRATOR_REQUEST_PAGE = "controller?command=goToForAdministrator&page=Request.jsp";
	
	public static final String GO_TO_FOR_ADMINISTRATOR = "WEB-INF/jsp/administrator/";
	
}
