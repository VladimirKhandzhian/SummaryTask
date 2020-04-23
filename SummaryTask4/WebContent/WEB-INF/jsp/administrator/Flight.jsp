<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

   	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>
<meta charset="UTF-8">
<title>Flight Page</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>

<jsp:useBean id="fly" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Flights"/>
	
<body>
<nav class="two">
	<ul>
		<li>
			<a href="MainPage.jsp"><fmt:message key='Main'/> <fmt:message key='page'/></a>
		</li>
		<li>
			<form action="controller" method="get">
				<input type="hidden" name="page" value="Flight.jsp">
				<input type="hidden" name="command" value="goToForAdministrator">
				<input type="submit" value="<fmt:message key='Flights'/>" class="submit1">
			</form>
		</li>
		<li>
			<form action="controller" method="get">
				<input type="hidden" name="page" value="Worker.jsp">
				<input type="hidden" name="command" value="goToForAdministrator">
				<input type="submit" value="<fmt:message key='Workers'/>" class="submit1">
			</form>
		</li>
		<li>
			<form action="controller" method="get">
				<input type="hidden" name="page" value="Airplane.jsp">
					<input type="hidden" name="command" value="goToForAdministrator">				
				<input type="submit" value="<fmt:message key='Airplanes'/>" class="submit1">
			</form>
		</li>
		<li>
			<form action="controller" method="get">
				<input type="hidden" name="page" value="Request.jsp">
				<input type="hidden" name="command" value="goToForAdministrator">				
				<input type="submit" value="<fmt:message key='Requests'/>" class="submit1">
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="command" value="logOut">
				<input type="submit" value="<fmt:message key='Log_Out'/>" class="submit1">
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="language" value="ru">
				<input type="hidden" name="command" value="language">
				<input type="hidden" name="page" value="Flight.jsp">
				<input type="submit" value="RU">  
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="language" value="en">
				<input type="hidden" name="command" value="language">
				<input type="hidden" name="page" value="Flight.jsp">
				<input type="submit" value="EN">  
			</form>
		</li>
	</ul>
</nav>
<h2 align="center"><fmt:message key='Flights'/></h2>
<div align="center">
	<table><tr><td></td>
			   <td align="center"><table border="1">
					<tr><td><fmt:message key='Flight_number'/></td>
       					<td><fmt:message key='Flight_name'/></td>
        				<td><fmt:message key='Place_of_departure'/></td>
        				<td><fmt:message key='Destination'/></td>
        				<td><fmt:message key='Date_and_time_of_departure'/></td>
        				<td><fmt:message key='Date_and_time_of_arrival'/></td>
        				<td><fmt:message key='Number_of_airplane'/></td>
        				<td><fmt:message key='Model'/></td>
        				<td><fmt:message key='Number_of_seats'/></td>
        				<td><fmt:message key='Price'/></td>
        				<td><fmt:message key='Number_of_brigad'/></td>
        				<td><fmt:message key='Flight_status'/></td>
        			</tr>
				<c:forEach items="${fly.getFlights()}" var="flight">
					<tr><td>${flight.flightNumber}</td>
       					<td>${flight.nameOfFlight}</td>
        				<td>${flight.departure}</td>
        				<td>${flight.destination}</td>
        				<td>${flight.departureDateTime}</td>
        				<td>${flight.destinationDateTime}</td>
        				<td>${flight.idAirplane}</td>
        				<td>${flight.model}</td>
        				<td>${flight.emptyOfSeats}</td>
        				<td>${flight.price}</td>
        				<td>${flight.idBrigad}</td>
        				<c:if test="${flight.flightStatus ne 'По рассписанию'}" >
        					<td><fmt:message key='${flight.flightStatus}'/></td>
        				</c:if>
        				<c:if test="${flight.flightStatus eq 'По рассписанию'}" >
        					<td><fmt:message key='Scheduled'/></td>
        				</c:if>
        				<td>
        					<form action="controller" method="get">
        						<input type="hidden" name="flightId" value="${flight.id}">
        						<input type="hidden" name="page" value="UpdateFlight.jsp">
        						<input type="hidden" name="command" value="goToForAdministrator">
        						<input type="submit" value="<fmt:message key='Update'/>">
        					</form>
        				</td>
        				<td>
        					<form action="controller" method="post">
        						<input type="hidden" name="flightId" value="${flight.id}">
        						<input type="hidden" name="command" value="deleteFlight">
        						<input type="submit" value="<fmt:message key='Delete'/>">
        					</form>
        				</td>
        			</tr>
     			</c:forEach>
				</table>
					<form action="controller" method="get">
						<input type="hidden" name="page" value="AddFlight.jsp">
						<input type="hidden" name="command" value="goToForAdministrator">
        				<input type="submit" value="<fmt:message key='Add_flight'/>">
					</form>
				</td>
			<td></td>
		</tr>
	</table>
</div><br><br>
</body>
</html>