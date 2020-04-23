<%@ tag import="ua.nure.khandzhian.SummaryTask4.db.DAO.DaoInterfaceFlights" %> 
<%@ tag import="ua.nure.khandzhian.SummaryTask4.db.DAO.mysql.DaoFlights" %>  <%-- Подключение java классов --%>
<%@ attribute name = "id" rtexprvalue="true"%>  <%-- Задаём атрибут --%>
<%
	DaoInterfaceFlights dao = new DaoFlights();
	int flightId = Integer.parseInt(id);
	int dayBeforeDeparture = 0;
	try {
		dayBeforeDeparture = dao.getDayBeforeDeparture(flightId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	if(dayBeforeDeparture > 0) {
		out.print("Days before departure " + dayBeforeDeparture);
	}else if(dayBeforeDeparture == 0){
		out.print("Departure will happen today");
	}else {
		out.print("Departure occurred " + Math.abs(dayBeforeDeparture) + " days ago");
	}
%>
