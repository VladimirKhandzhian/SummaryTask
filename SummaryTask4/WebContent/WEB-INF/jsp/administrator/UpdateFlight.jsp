<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

   	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update flight</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<h2 align = "center"><fmt:message key='Information_about_flight'/></h2>
		<div class = "justified">
       		<form action="controller" method="post">
				<fieldset>
					<legend><fmt:message key='Flight_number'/></legend>
					<input name="flightNumber" value="${flight.flightNumber}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Flight_name'/></legend>
					<input name="nameOfFlight" value="${flight.nameOfFlight}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Place_of_departure'/></legend>
					<input name="departure" value="${flight.departure}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Destination'/></legend>
					<input name="destination" value="${flight.destination}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Date_and_time_of_departure'/></legend>
					<input type="datetime-local" name="departureDateTime" value="${flight.departureDateTime}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Date_and_time_of_arrival'/></legend>
					<input type="datetime-local" name="destinationDateTime" value="${flight.destinationDateTime}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Airplane'/></legend>
					<select name="airplaneId">
						<option value="${flight.idAirplane}">
							${flight.idAirplane}. ${model} ${numberOfSeats}
						</option>
						<c:forEach items="${emptyModel}" var="emptyModel">
							<option value="${emptyModel.idAirplane}">
								${emptyModel.idAirplane}. ${emptyModel.model} ${emptyModel.numberOfSeats}
							</option>
						</c:forEach>
					</select>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Number_of_brigad'/></legend>
					<select name="brigadId">
						<option value="${flight.idBrigad}">
							<fmt:message key='Brigad'/> № ${flight.idBrigad}
						</option>
						<c:forEach items="${emptyBrigad}" var="emptyBrigad">
							<option value="${emptyBrigad.idBrigads}">
								<fmt:message key='Brigad'/> № ${emptyBrigad.idBrigads}
							</option>
						</c:forEach>
					</select>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Price_of_ticket'/></legend>
					<input name="price" value="${flight.price}"><br>
				</fieldset>
				<div align="center">
					<input type="hidden" name="flightId" value="${flight.id}">
					<input type="hidden" name="command" id="command" value="updateFlight">
					<input type="submit" value="<fmt:message key='Update'/>">
				</div>
			</form><br>
    	</div>
</body>
</html>