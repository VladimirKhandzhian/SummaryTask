<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>
<meta charset="UTF-8">
<title>Worker Page</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<style>
	table{
		margin: 0 auto;
		vertical-align: middle;
	}
</style>
</head>

<jsp:useBean id="fly" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Workers"/>
<jsp:setProperty name="fly" property="idWorker" value="${workerId}"/>
<body>
<nav class="two">
	<ul>
		<li>
			<a href="MainPage.jsp"><fmt:message key='Main'/> <fmt:message key='page'/></a>
		</li>
		<li style="float: right;">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="logOut">
				<input type="submit" value="<fmt:message key='Log_Out'/>" class="submit1">
			</form>
		</li>
	</ul>
</nav>
<c:if test="${not empty fly.getFlightsByWorkerId()}">
<table><tr><td></td>
			<td><table border="1">
					<tr><td><fmt:message key='Flight_number'/></td>
       					<td><fmt:message key='Flight_name'/></td>
        				<td><fmt:message key='Place_of_departure'/></td>
        				<td><fmt:message key='Destination'/></td>
        				<td><fmt:message key='Date_and_time_of_departure'/></td>
        				<td><fmt:message key='Date_and_time_of_arrival'/></td>
        				<td><fmt:message key='Number_of_airplane'/></td>
        				<td><fmt:message key='Flight_status'/></td>
        			</tr>
				<c:forEach items="${fly.getFlightsByWorkerId()}" var="flights">
					<tr><td>${flights.flightNumber}</td>
       					<td>${flights.nameOfFlight}</td>
        				<td>${flights.departure}</td>
        				<td>${flights.destination}</td>
        				<td>${flights.departureDateTime}</td>
        				<td>${flights.destinationDateTime}</td>
        				<td>${flights.idAirplane}</td>
        				<c:if test="${flights.flightStatus ne 'По рассписанию'}" >
        					<td><fmt:message key='${flights.flightStatus}'/></td>
        				</c:if>
        				<c:if test="${flights.flightStatus eq 'По рассписанию'}" >
        					<td><fmt:message key='Scheduled'/></td>
        				</c:if>
        			</tr>
     			</c:forEach>
				</table></td>
			<td></td>
		</tr>
</table>
</c:if>
<c:if test="${empty fly.getFlightsByWorkerId()}">
	<h2 align="center">Ваше рассписание свободно</h2>
</c:if>
</body>
</html>