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
<title>Add Flight</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<c:if test="${administratorId == null}">
	 <c:redirect url="MainPage.jsp" />
</c:if>

<h2 align = "center"><fmt:message key='Information_about_flight'/></h2>
		<div class = "justified">
       		<form action="controller" method="post">
				<fieldset>
					<legend><fmt:message key='Flight_number'/></legend>
					<input name="flightNumber"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Flight_name'/></legend>
					<input name="nameOfFlight"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Place_of_departure'/></legend>
					<input name="departure"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Destination'/></legend>
					<input name="destination"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Date_and_time_of_departure'/></legend>
					<input type="datetime-local" name="departureDateTime"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Date_and_time_of_arrival'/></legend>
					<input type="datetime-local" name="destinationDateTime"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Airplane'/></legend>
					<select name="airplaneId">
					<c:forEach items="${airplanes}" var="airplane">
						<option value="${airplane.idAirplane}">
							${airplane.idAirplane}. ${airplane.model} ${airplane.numberOfSeats}
						</option>
					</c:forEach>
					</select>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Number_of_brigad'/></legend>
					<select name="brigadId">
					<c:forEach items="${brigads}" var="brigad">
						<option value="${brigad.idBrigads}"><fmt:message key='Brigad'/> № ${brigad.idBrigads}</option>
					</c:forEach>
					</select>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Price_of_ticket'/></legend>
					<input name="price"><br>
				</fieldset>
				<div align="center">
					<input type="hidden" name="command" value="addFlight">
					<input type="submit" value="<fmt:message key='Add'/>">
				</div>
			</form><br>
    	</div>
</body>
</html>