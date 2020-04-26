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
<title>Update Airplane</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<c:if test="${administratorId == null}">
	 <c:redirect url="MainPage.jsp" />
</c:if>

<h2 align = "center"><fmt:message key='Information_about_airplane'/></h2>
		<div class = "justified">
       		<form action="controller" method="post">
				<fieldset>
					<legend><fmt:message key='Model'/></legend>
					<input name="model" value="${airplane.model}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Number_of_seats'/></legend>
					<input name="numberOfSeats" value="${airplane.numberOfSeats}"><br>
				</fieldset>
				<div align="center">
					<input type="hidden" name="airplaneId" value="${airplane.idAirplane}">
					<input type="hidden" name="command" value="updateAirplane">
					<input type="submit" value="<fmt:message key='Update'/>">
				</div>
			</form><br>
    	</div>
</body>
</html>