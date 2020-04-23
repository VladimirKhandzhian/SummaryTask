<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="tag" uri="/WEB-INF/MyTagDescriptor.tld" %>
<%@ taglib prefix="fileTag" tagdir="/WEB-INF/tags" %>  <%-- Подключаем тег --%>

	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>
<meta charset="UTF-8">
<title>Passenger List Page</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style3.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style4.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<nav class="two">
	<ul>
		<li><a href="javascript:history.back()" class="a"><fmt:message key='Back'/></a></li>
	</ul>
</nav>
<c:if test="${not empty passengerList}">
<div align="center">
	<tag:MyTag id="${flightId}"></tag:MyTag>
	<fileTag:FileTag id="${flightId}"></fileTag:FileTag> <%-- Вызываем тег и задаем атрибут id --%>
	<table border="1">
		<tr>
			<td><fmt:message key='Number_of_ticket'/></td>
			<td><fmt:message key='Passport_number'/></td>
			<td><fmt:message key='First_name'/></td>
			<td><fmt:message key='Second_name'/></td>
			<td><fmt:message key='Email'/></td>
		</tr>
			<c:forEach items="${passengerList}" var="passList">
				<tr>
					<td>${passList.ticketId}</td>
					<td>${passList.passportId}</td>
					<td>${passList.firstName}</td>
					<td>${passList.secondName}</td>
					<td>${passList.email}</td>
				</tr>
			</c:forEach>
	</table>
</div>
</c:if>

<c:if test="${empty passengerList}">
	<h2 align="center"><fmt:message key='No_flight_tickets_currently_sold'/></h2>
</c:if>

</body>
</html>