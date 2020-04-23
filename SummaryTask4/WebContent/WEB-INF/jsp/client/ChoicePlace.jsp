<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>
<meta charset="UTF-8">
<title>Choice Page</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style3.css" rel="stylesheet" type="text/css"/>  
<style type="text/css">    
	* { margin: 0; padding: 0; }
    p { padding: 10px; } 
    .sub{
    	width: 27px;
    	height: 20px;
    }
    button{
    	width: 27px;
    	height: 20px;
    }
    table{ 
    	position: relative;
    	background: url('resources/images/airplane.png') no-repeat;
    	background-size: cover;
    }
    #left { position: absolute; left: 0; top: 50px; width: 50%; }
    #right { position: absolute; right: 0; top: 50px; width: 50%; } 
</style>      
</head>	
<body>
<nav class="two">
	<ul>
		<li>
			<a href="MainPage.jsp"><fmt:message key='Main'/> <fmt:message key='page'/></a>
		</li>
		<c:if test="${passportId != null}">
			<li>
				<form action="controller" method="get">
					<input type="hidden" name="command" value="goToCabinet">
					<input type="submit" value="<fmt:message key='Cabinet'/>" class="submit1">
				</form>
			</li>
		</c:if>
	</ul>
</nav>

	<div id="left">
		<h2 style="margin-left: 125px"><fmt:message key='Places'/></h2>
		<table>
			<tr style="height: 240px;"></tr>
				<c:forEach items="${places}" var="place">
					<c:forEach items="${occupiedThisClientPlaces}" var="occupiedThisClientPlace">
						<c:if test="${occupiedThisClientPlace.ticketId == place}">
							<c:set var="stat" scope="request" value="${place}"/> <!-- было session -->
							<c:if test="${place % 9 != 0}">
								<c:if test="${place % 3 == 0}">
									<td><form action="controller" method="get">
											<input type="hidden" name="flightId" value="${flightId}">
											<input type="hidden" name="ticketId" value="${place}">
											<input type="hidden" name="command" value="deleteFromBasket">
											<input type="submit" value="${place}" style="background: green" class="sub">
										</form>
									</td>
									<td style="width: 27px;"></td>
								</c:if>
								<c:if test="${place % 3 != 0}">
									<td><form action="controller" method="get">
											<input type="hidden" name="flightId" value="${flightId}">
											<input type="hidden" name="ticketId" value="${place}">
											<input type="hidden" name="command" value="deleteFromBasket">
											<input type="submit" value="${place}" style="background: green" class="sub">
										</form>
									</td>	
								</c:if>
							</c:if>
							<c:if test="${place % 9 == 0}">
								<td><form action="controller" method="get">
										<input type="hidden" name="flightId" value="${flightId}">
										<input type="hidden" name="ticketId" value="${place}">
										<input type="hidden" name="command" value="deleteFromBasket">
										<input type="submit" value="${place}" style="background: green" class="sub">
									</form>
								</td><tr/>	
							</c:if>
						</c:if>
					</c:forEach>
					<c:forEach items="${occupiedPlaces}" var="occupiedPlace">
						<c:if test="${occupiedPlace.ticketId == place and place != stat}">
							<c:set var="stat" scope="request" value="${place}"/> <!-- было session -->
							<c:if test="${place % 9 != 0}">
								<c:if test="${place % 3 == 0}">
									<td align="center"><button style="background: red" disabled>${place}</button></td>
									<td style="width: 27px;"></td>
								</c:if>
								<c:if test="${place % 3 != 0}">
									<td align="center"><button style="background: red" disabled>${place}</button></td>
								</c:if>
							</c:if>
							<c:if test="${place % 9 == 0}">
								<td align="center"><button style="background: red" disabled>${place}</button></td><tr/>
							</c:if>
						</c:if>
					</c:forEach>
					<c:if test="${place != stat}">
						<c:if test="${place % 9 != 0}">
							<c:if test="${place % 3 == 0}">
								<td><form action="controller" method="get">
										<input type="hidden" name="flightId" value="${flightId}">
										<input type="hidden" name="ticketId" value="${place}">
										<input type="hidden" name="command" value="addToBasket">
										<input type="submit" value="${place}" class="sub">
									</form>
								</td>	
								<td style="width: 27px;"></td>
							</c:if>
							<c:if test="${place % 3 != 0}">
								<td><form action="controller" method="get">
										<input type="hidden" name="flightId" value="${flightId}">
										<input type="hidden" name="ticketId" value="${place}">
										<input type="hidden" name="command" value="addToBasket">
										<input type="submit" value="${place}" class="sub">
									</form>
								</td>
							</c:if>
						</c:if>
						<c:if test="${place % 9 == 0}">
							<td><form action="controller" method="get">
									<input type="hidden" name="flightId" value="${flightId}">
									<input type="hidden" name="ticketId" value="${place}">
									<input type="hidden" name="command" value="addToBasket">
									<input type="submit" value="${place}" class="sub">
								</form>
							</td><tr/>
						</c:if>
					</c:if>
				</c:forEach>
		</table>
	</div>

	<div id="right">
		<c:if test="${not empty occupiedThisClientPlaces}">	
		<h2 style="margin-left:60px;"><fmt:message key='My'/> <fmt:message key='data'/></h2>
		<form action="controller" method="get" style="margin-left:50px;">
       		<div>
				<div><fmt:message key='Passport_number'/></div>
				<input name="passportId" value="${passenger.passportId}" readonly>
			</div>
			<div>
				<div><fmt:message key='First_name'/></div>
				<input name="firstName" value="${passenger.firstName}" readonly><br>
			</div>
			<div>
				<div><fmt:message key='Second_name'/></div>
				<input name="secondName" value="${passenger.secondName}" readonly><br>
			</div>
			<div>
				<div><fmt:message key='Email'/></div>
				<input name="email" value="${passenger.email}" readonly><br>
			</div>
			<div>
				<div><fmt:message key='Card_data'/></div>
				<input name="cardNumber"><br>
			</div>
			<div>
				<input name="date" placeholder="00/00" style="width: 40px;">   <input name="CVV" placeholder="CVV" style="width: 40px;">
			</div>
			<div><fmt:message key='Sum_to_paid'/>: ${sumToPaid} UAH</div>
			<div>
				<input type="hidden" name="flightId" value="${flightId}">
				<input type="hidden" name="command" value="buyTicket">
				<input type="submit" value="<fmt:message key='Paid'/>" style="margin-left:50px;">
			</div>
		</form><br>
		</c:if>
	</div>
</body>
</html>