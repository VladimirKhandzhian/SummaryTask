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
<link href="resources/style/style3.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style4.css" rel="stylesheet" type="text/css"/>        
</head>

<jsp:useBean id="fly" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Flights"/>
	
<body>
<nav class="two">
	<ul>
		<li>
			<a href="MainPage.jsp"><fmt:message key='Main'/> <fmt:message key='page'/></a>
		</li>
		<c:if test="${passportId == null and workerId == null and administratorId == null}">
			<li>
				<a href="Login.jsp"><fmt:message key='Sign_In'/></a>
			</li>
			<li>
				<a href="Registration.jsp"><fmt:message key='Registration'/></a>
			</li>
		</c:if>
		<c:if test="${passportId != null}">
			<li>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="goToCabinet">
					<input type="submit" value="<fmt:message key='Cabinet'/>" class="submit1">
				</form>
			</li>
		</c:if>
		<c:if test="${workerId != null or administratorId != null}">
			<li>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="goToFromMainPage">
					<input type="submit" value="<fmt:message key='For_worker'/>" class="submit1">
				</form>
			</li>
		</c:if>
		<c:if test="${workerId != null or administratorId != null or passportId != null}">
		 	<li style="float: right;">
				<form action="controller" method="get">
					<input type="hidden" name="command" value="logOut">
					<input type="submit" value="<fmt:message key='Log_Out'/>" class="submit1">
				</form>
			</li>
		</c:if>	
			<li style="float: right;">
				<form action="controller" method="get">
					<input name="flightNumber" placeholder="<fmt:message key='Number_of_flight'/>">
					<input type="hidden" name="command" value="searchFlightByNumber">
					<input type="submit" value="<fmt:message key='Search'/>">  
				</form>
			</li>
			<li style="float: right;">
				<form action="controller" method="get">
					<input type="hidden" name="language" value="ru">
					<input type="hidden" name="command" value="language">
					<input type="hidden" name="page" value="MainPage.jsp">
					<input type="submit" value="RU">  
				</form>
			</li>
			<li style="float: right;">
				<form action="controller" method="get">
					<input type="hidden" name="language" value="en">
					<input type="hidden" name="command" value="language">
					<input type="hidden" name="page" value="MainPage.jsp">
					<input type="submit" value="EN">  
				</form>
			</li>
	</ul>
</nav>
<div class="split left">
	<nav class="three">
		<div>
			<form action="controller" action="post">
				<input type="hidden" name="command" value="sort">
				<input type="hidden" name="typeOfSort" value="idByAsk">
				<input type="submit" value="<fmt:message key='Ascending_Flight_Number'/>" class="submit2"> 
			</form>
		</div>
		<div>
			<form action="controller" action="post">
				<input type="hidden" name="command" value="sort">
				<input type="hidden" name="typeOfSort" value="idByDesc">
				<input type="submit" value="<fmt:message key='Descending_Flight_Number'/>" class="submit2"> 
			</form>
		</div>
		<div>
			<form action="controller" action="post">
				<input type="hidden" name="command" value="sort">
				<input type="hidden" name="typeOfSort" value="nameByAsk">
				<input type="submit" value="<fmt:message key='From_A_to_Z'/>" class="submit2"> 
			</form>
		</div>
		<div>
			<form action="controller" action="post">
				<input type="hidden" name="command" value="sort">
				<input type="hidden" name="typeOfSort" value="nameByDesc">
				<input type="submit" value="<fmt:message key='From_Z_to_A'/>" class="submit2"> 
			</form>
		</div>
	</nav>
</div>

<div class="split right">
<div align="center">
	<form action="controller" method="get">
		<fmt:message key='Where_from'/> <input name="departure"> 
		<fmt:message key='Where_to'/> <input name="destination">
		<fmt:message key='Departure_date'/> <input type="date" name="departureDate">
		<input type="hidden" name="command" value="searchFlightByAddress">
		<input type="submit" value="<fmt:message key='Search'/>">
	</form>
</div>
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
        				<td><fmt:message key='Number_of_seats'/></td>
        				<td><fmt:message key='Price'/></td>
        				<td><fmt:message key='Flight_status'/></td>
        			</tr>
				<c:forEach items="${fly.getFlights()}" var="flight">
					<tr><td>${flight.flightNumber}</td>
       					<td>${flight.nameOfFlight}</td>
        				<td>${flight.departure}</td>
        				<td>${flight.destination}</td>
        				<td>${flight.departureDateTime}</td>
        				<td>${flight.destinationDateTime}</td>
        				<td>${flight.emptyOfSeats}</td>
        				<td>${flight.price}</td>
        				<c:if test="${flight.flightStatus ne 'По рассписанию'}" >
        					<td><fmt:message key='${flight.flightStatus}'/></td>
        				</c:if>
        				<c:if test="${flight.flightStatus eq 'По рассписанию'}" >
        					<td><fmt:message key='Scheduled'/></td>
        				</c:if>
        					<c:if test="${flight.emptyOfSeats != 0 and flight.flightStatus eq 'По рассписанию' and passportId != null}">
        						<td>
        							<form action="controller" method="get">
        								<input type="hidden" name="flightId" value="${flight.id}">
        								<input type="hidden" name="command" value="goToTheChoiceOfPlace">
        								<input type="submit" value="<fmt:message key='Order_a_ticket'/>">
        							</form>
								</td>
        					</c:if>
        					<c:if test="${flight.emptyOfSeats == 0 or flight.flightStatus ne 'По рассписанию' or passportId == null}">
        						<td>
        							<input type="submit" value="<fmt:message key='Order_a_ticket'/>" disabled>
								</td>
        					</c:if>
        			</tr>
     			</c:forEach>
				</table>
				</td>
			<td></td>
		</tr>
	</table>
</div>
</div>
</body>
</html>