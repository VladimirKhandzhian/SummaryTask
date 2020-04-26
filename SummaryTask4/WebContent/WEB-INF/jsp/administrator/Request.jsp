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

<jsp:useBean id="requests" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Requests"/>
	
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
				<input type="hidden" name="page" value="Request.jsp">
				<input type="submit" value="RU">  
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="language" value="en">
				<input type="hidden" name="command" value="language">
				<input type="hidden" name="page" value="Request.jsp">
				<input type="submit" value="EN">  
			</form>
		</li>
	</ul>
</nav>

<h2 align="center"><fmt:message key='Requests'/></h2>
<div align="center">
	<table><tr><td></td>
			   <td align="center"><table border="1">
					<tr><td><fmt:message key='Number_of_request'/></td>
						<td><fmt:message key='Text_of_request'/></td>
						<td><fmt:message key='Status_request'/></td>
					</tr>
				<c:forEach items="${requests.getRequests()}" var="request">
					<tr><td>${request.idRequest}</td>
       					<td>${request.textOfRequest}</td>
						<c:if test="${request.response eq 'Не рассмотрена'}">
							<td><fmt:message key='Not_reviewed'/></td>
						</c:if>
						<c:if test="${request.response ne 'Не рассмотрена'}">
							<td><fmt:message key='${request.response}'/></td>
						</c:if>
        				<td>
        					<form action="controller" method="post">
        						<select name="status">
  									<option value="Выполнена"><fmt:message key='Done'/></option>
  									<option value="Отклонена"><fmt:message key='Rejected'/></option>
  									<option value="Не рассмотрена"><fmt:message key='Not_reviewed'/></option>
								</select>
        						<input type="hidden" name="requestId" value="${request.idRequest}">
        						<input type="hidden" name="command" value="updateStatusRequest">
        						<input type="submit" value="<fmt:message key='Confirm'/>">
        					</form>
        				</td>
        			</tr>
     			</c:forEach>
				</table>
				</td>
			<td></td>
		</tr>
	</table>
</div><br><br>
</body>
</html>