<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>
<meta charset="UTF-8">
<title>Administrator Page</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>

<jsp:useBean id="airplane" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Airplane"/>
	
<body>

<c:if test="${administratorId == null}">
	 <c:redirect url="MainPage.jsp" />
</c:if>

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
				<input type="hidden" name="page" value="Airplane.jsp">
				<input type="submit" value="RU">  
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="language" value="en">
				<input type="hidden" name="command" value="language">
				<input type="hidden" name="page" value="Airplane.jsp">
				<input type="submit" value="EN">  
			</form>
		</li>
	</ul>
</nav>

<h2 align="center"><fmt:message key='Airplanes'/></h2>
<div align="center">
	<table><tr><td></td>
			   <td align="center"><table border="1">
					<tr><td><fmt:message key='Number_of_airplane'/></td>
       					<td><fmt:message key='Model'/></td>
       					<td><fmt:message key='Number_of_seats'/></td>
        			</tr>
				<c:forEach items="${airplane.getAirplane()}" var="airplane">
					<tr><td>${airplane.idAirplane}</td>
       					<td>${airplane.model}</td>
        				<td>${airplane.numberOfSeats}</td>
        				<td>
        					<form action="controller" method="get">
        						<input type="hidden" name="airplaneId" value="${airplane.idAirplane}">
        						<input type="hidden" name="page" value="UpdateAirplane.jsp">
        						<input type="hidden" name="command" value="goToForAdministrator">
        						<input type="submit" value="<fmt:message key='Update'/>">
        					</form>
        				</td>
        				<td>
        					<form action="controller" method="post">
        						<input type="hidden" name="airplaneId" value="${airplane.idAirplane}">
        						<input type="hidden" name="command" value="deleteAirplane">
        						<input type="submit" value="<fmt:message key='Delete'/>">
        					</form>
        				</td>
        			</tr>
     			</c:forEach>
				</table>
					<form action="controller" method="get">
						<input type="hidden" name="page" value="AddAirplane.jsp">
						<input type="hidden" name="command" value="goToForAdministrator">
        				<input type="submit" value="<fmt:message key='Add_airplane'/>">
					</form>
				</td>
			<td></td>
		</tr>
	</table>
</div><br><br>
</body>
</html>