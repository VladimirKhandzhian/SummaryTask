<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>
<meta charset="UTF-8">
<title>Log In Account</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<c:if test="${passportId == null}">
	 <c:redirect url="MainPage.jsp" />
</c:if>

<nav class="two">
	<ul>
		<li>
			<a href="MainPage.jsp"><fmt:message key='Main'/> <fmt:message key='page'/></a>
		</li>
		<li>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="goToCabinet">
				<input type="submit" value="<fmt:message key='Cabinet'/>" class="submit1">
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
				<input type="hidden" name="page" value="Client.jsp">
				<input type="submit" value="RU">  
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="language" value="en">
				<input type="hidden" name="command" value="language">
				<input type="hidden" name="page" value="Client.jsp">
				<input type="submit" value="EN">  
			</form>
		</li>
	</ul>
</nav>

	<h2 align = "center"><fmt:message key='My'/> <fmt:message key='data'/></h2>
	<div class = "justified">
       	<table border="1">
       		<tr><td><fmt:message key='Passport_number'/></td><td>${passenger.passportId}</td></tr>
       		<tr><td><fmt:message key='First_name'/></td><td>${passenger.firstName}</td></tr>
       		<tr><td><fmt:message key='Second_name'/></td><td>${passenger.secondName}</td></tr>
       		<tr><td><fmt:message key='Email'/></td><td>${passenger.email}</td></tr>
       		<tr><td>
       				<form action="controller" method="get">
       					<input type="hidden" name="command" value="goToUpdateClient">
						<input type="submit" value="<fmt:message key='Update'/>">
					</form>
       			</td>
       			<td>
					<form action="controller" method="get">
       					<input type="hidden" name="command" value="deleteClient">
						<input type="submit" value="<fmt:message key='Delete_account'/>">
					</form>
				</td></tr>
       	</table>
    </div>
    
<c:if test="${not empty orders}">
<h2 align = "center"><fmt:message key='Orders'/></h2>
<div class = "justified">
	<table><tr><td></td>
			   <td align="center">
			   <table border="1">
					<tr><td><fmt:message key='Flight_number'/></td>
       					<td><fmt:message key='Flight_name'/></td>
        				<td><fmt:message key='Place_of_departure'/></td>
        				<td><fmt:message key='Destination'/></td>
        				<td><fmt:message key='Date_and_time_of_departure'/></td>
        				<td><fmt:message key='Date_and_time_of_arrival'/></td>
        				<td><fmt:message key='Number_of_ticket'/></td>
        			</tr>
				<c:forEach items="${orders}" var="order">
					<tr><td>${order.flightNumber}</td>
       					<td>${order.nameOfFlight}</td>
        				<td>${order.departure}</td>
        				<td>${order.destination}</td>
        				<td>${order.departureDateTime}</td>
        				<td>${order.destinationDateTime}</td>
        				<td>${order.ticketId}</td>
        			</tr>
     			</c:forEach>
				</table>
				</td>
			<td></td>
		</tr>
	</table>
</div>
</c:if>
</body>
</html>