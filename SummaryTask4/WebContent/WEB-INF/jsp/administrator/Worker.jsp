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

<jsp:useBean id="worker" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Workers"/>
	
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
				<input type="hidden" name="page" value="Worker.jsp">
				<input type="submit" value="RU">  
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="language" value="en">
				<input type="hidden" name="command" value="language">
				<input type="hidden" name="page" value="Worker.jsp">
				<input type="submit" value="EN">  
			</form>
		</li>
	</ul>
</nav>

<h2 align="center"><fmt:message key='Workers'/></h2>
<div align="center">
	<table><tr><td></td>
			   <td align="center"><table border="1">
					<tr><td><fmt:message key='Number_of_worker'/></td>
       					<td><fmt:message key='First_name'/></td>
        				<td><fmt:message key='Second_name'/></td>
        				<td><fmt:message key='Profession'/></td>
        				<td><fmt:message key='Email'/></td>
        				<td><fmt:message key='Phone_number'/></td>
        			</tr>
				<c:forEach items="${worker.getWorkers()}" var="worker">
					<tr><td>${worker.idWorker}</td>
       					<td>${worker.firstName}</td>
        				<td>${worker.secondName}</td>
        				<td>${worker.profession}</td>
        				<td>${worker.email}</td>
        				<td>${worker.phoneNumber}</td>
        				<td>
        					<form action="controller" method="get">
        						<input type="hidden" name="workerId" value="${worker.idWorker}">
        						<input type="hidden" name="page" value="UpdateWorker.jsp">
        						<input type="hidden" name="command" value="goToForAdministrator">	
        						<input type="submit" value="<fmt:message key='Update'/>">
        					</form>
        				</td>
        				<td>
        					<form action="controller" method="post">
        						<input type="hidden" name="workerId" value="${worker.idWorker}">
        						<input type="hidden" name="command" value="deleteWorker">	
        						<input type="submit" value="<fmt:message key='Delete'/>">
        					</form>
        				</td>
        			</tr>
     			</c:forEach>
				</table>
					<form action="controller" method="get">
						<input type="hidden" name="page" value="AddWorker.jsp">
						<input type="hidden" name="command" value="goToForAdministrator">
        				<input type="submit" value="<fmt:message key='Add_worker'/>">
					</form>
				</td>
			<td></td>
		</tr>
	</table>
</div><br><br>
</body>
</html>