<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/>
	
<html>
<head>
<meta charset="UTF-8">
<title>Dispatcher Page</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style3.css" rel="stylesheet" type="text/css"/>
</head>

<jsp:useBean id="brigads" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Brigads"/>
	
<jsp:useBean id="fly" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Flights"/>
	
<jsp:useBean id="workers" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Workers"/>
	
<jsp:useBean id="requests" scope="request" 
	class="ua.nure.khandzhian.SummaryTask4.db.entity.Workers"/>
<jsp:setProperty name="requests" property="idWorker" value="${workerId}"/>

<body>
<div id="zatemnenie">
      <div id="okno">
      <h2><fmt:message key='Create_brigad'/></h2>
       	<form action="controller" method="post">
			<fieldset>
				<legend><fmt:message key='Number_of_brigad'/></legend>
				<input type="number" name="brigadId" min="${brigads.getMaxBrigads()}" value="${brigads.getMaxBrigads()}" step="1"><br>
			</fieldset>					
			<fieldset>
				<legend><fmt:message key='Select_commander'/></legend>
				<select name="workerId">
  					<c:forEach items="${workers.getEmptyCommander()}" var="emptyCommander">
  						<option value="${emptyCommander.idWorker}">
  							${emptyCommander.idWorker}. ${emptyCommander.firstName} ${emptyCommander.secondName}
  						</option>
  					</c:forEach>
				</select>
			</fieldset><br>
			<div align="center">
				<input type="hidden" name="command" value="createBrigad">
				<input type="submit" value=<fmt:message key='Create'/>>
			</div>
		</form>
        <a href="#" class="close"><fmt:message key='Close_window'/></a>
      </div>
</div>

<div id="zatemnenie1">
      <div id="okno">
      <h2><fmt:message key='Request'/></h2>
       	<form action="controller" method="post">
       		<textarea rows="10" cols="35" name = "textOfRequest"></textarea>
       		<input type="hidden" name="command" value="sendRequest">
       		<input type="submit" value="<fmt:message key='Send'/>">
       	</form>
        <a href="#" class="close"><fmt:message key='Close_window'/></a>
      </div>
</div>

<nav class="two">
	<ul>
		<li>
			<a href="MainPage.jsp"><fmt:message key='Main'/> <fmt:message key='page'/></a>
		</li>
		<c:if test="${not empty workers.getEmptyCommander()}">
  			<li><a href="#zatemnenie"><fmt:message key='Create_brigad'/></a></li>
  		</c:if>
  		<li><a href="#zatemnenie1"><fmt:message key='Send_request_to_administrator'/></a></li> 
  		<li>
  			<div class="dropdown">
			<div><button class="submit1"><fmt:message key='Request'/></button>
				<div class="dropdown-content">
       			 	<ul class="three">
						<li><c:if test="${not empty requests.getRequestsByWorkerId()}">
							<table border="1">
								<tr><td><fmt:message key='Number_of_request'/></td>
									<td><fmt:message key='Text_of_request'/></td>
									<td><fmt:message key='Response'/></td>
								</tr>
									<c:forEach items="${requests.getRequestsByWorkerId()}" var="request">
										<tr><td>${request.idRequest}</td>
											<td>${request.textOfRequest}</td>
											<c:if test="${request.response eq 'Не рассмотрена'}">
												<td><fmt:message key='Not_reviewed'/></td>
											</c:if>
											<c:if test="${request.response ne 'Не рассмотрена'}">
												<td><fmt:message key='${request.response}'/></td>
											</c:if>
										</tr>
									</c:forEach>
							</table>
							</c:if>
						</li>
					</ul>	
				</div>
			</div>
			</div>
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
				<input type="hidden" name="page" value="Dispatcher.jsp">
				<input type="submit" value="RU">  
			</form>
		</li>
		<li style="float: right;">
			<form action="controller" method="get">
				<input type="hidden" name="language" value="en">
				<input type="hidden" name="command" value="language">
				<input type="hidden" name="page" value="Dispatcher.jsp">
				<input type="submit" value="EN">  
			</form>
		</li>
  	</ul>
</nav> 	


<h2 align = "center"><fmt:message key='Flights'/></h2>
<table style="margin: 0 auto"><tr><td></td>
			<td><table border="1">
	<tr><td><fmt:message key='Flight_number'/></td>
       					<td><fmt:message key='Flight_name'/></td>
        				<td><fmt:message key='Place_of_departure'/></td>
        				<td><fmt:message key='Destination'/></td>
        				<td><fmt:message key='Date_and_time_of_departure'/></td>
        				<td><fmt:message key='Date_and_time_of_arrival'/></td>
        				<td><fmt:message key='Number_of_airplane'/></td>
        				<td><fmt:message key='Model'/></td>
        				<td><fmt:message key='Number_of_seats'/></td>
        				<td><fmt:message key='Number_of_brigad'/></td>
        				<td><fmt:message key='Flight_status'/></td>
        			</tr>
				<c:forEach items="${fly.getFlights()}" var="flights">
					<tr><td>${flights.flightNumber}</td>
       					<td>${flights.nameOfFlight}</td>
        				<td>${flights.departure}</td>
        				<td>${flights.destination}</td>
        				<td>${flights.departureDateTime}</td>
        				<td>${flights.destinationDateTime}</td>
        				<td>${flights.idAirplane}</td>
        				<td>${flights.model}</td>
        				<td>${flights.emptyOfSeats}</td>
        				<td>${flights.idBrigad}</td>
        				<c:if test="${flights.flightStatus ne 'По рассписанию'}" >
        					<td><fmt:message key='${flights.flightStatus}'/></td>
        				</c:if>
        				<c:if test="${flights.flightStatus eq 'По рассписанию'}" >
        					<td><fmt:message key='Scheduled'/></td>
        				</c:if>
        				<td><form action="controller" method="post">
        						<select name="status">
  									<option disabled><fmt:message key='Update_status_of_flight'/></option>
  									<option value="По рассписанию"><fmt:message key='According_to_schedule'/></option>
  									<option value="Ожидается"><fmt:message key='Expected'/></option>
  									<option value="Прибыл"><fmt:message key='Arrived'/></option>
  									<option value="Отменён"><fmt:message key='Canceled'/></option>
								</select><br>
        						<input type="hidden" name="flightId" value="${flights.id}">
        						<input type="hidden" name="command" value="updateStatusFlights">
								<input type="submit" value="<fmt:message key='Confirm_changes'/>">
        					</form>
        				</td>
        				<td>
        					<form action="controller" method="get">
        						<input type="hidden" name="flightId" value="${flights.id}">
        						<input type="hidden" name="command" value="goToPassengerList">
								<input type="submit" value="<fmt:message key='Passenger_list'/>">
        					</form>
        				</td>
        			</tr>
     			</c:forEach>
				</table></td>
			<td></td>
		</tr>
</table><br><br>
<h2 align = "center"><fmt:message key='Brigads'/></h2>
<c:forEach items="${brigads.getDistinctBrigads()}" var="brigadsNum">
	<ul class="three">
		<li><h3><fmt:message key='Brigad'/> № ${brigadsNum.idBrigads}</h3>
			<table border="1">
				<tr>
					<td><fmt:message key='Number_of_worker'/></td>
					<td><fmt:message key='First_name'/></td>
					<td><fmt:message key='Second_name'/></td>
					<td><fmt:message key='Position'/></td>
				</tr>
					<c:forEach items="${brigads.getBrigads()}" var="brigad">
						<c:if test="${brigadsNum.idBrigads == brigad.idBrigads}">
							<tr>
								<td>${brigad.idWorker}</td>
								<td>${brigad.firstName}</td>
								<td>${brigad.secondName}</td>
								<td>${brigad.profession}</td>
								<td><form action="controller" method="post">
										<input type="hidden" name="workerId" value="${brigad.idWorker}">
										<input type="hidden" name="brigadId" value="${brigad.idBrigads}">
										<input type="hidden" name="command" value="deleteFromBrigad">
										<input type="submit" value="<fmt:message key='Delete_from_brigad'/>">
									</form>
								</td>
							</tr>
						</c:if>
					</c:forEach>
			</table>
			
			<c:if test="${not empty workers.getEmptyWorker()}">
				<div class="dropdown">
					<div><button><fmt:message key='Add_worker'/></button>
						<div class="dropdown-content">
       			 			<ul class="three">
								<li>
									<table border="1">
										<tr>
											<td><fmt:message key='Number_of_worker'/></td>
											<td><fmt:message key='First_name'/></td>
											<td><fmt:message key='Second_name'/></td>
											<td><fmt:message key='Position'/></td>
										</tr>
										<c:forEach items="${workers.getEmptyWorker()}" var="emptyWorker">
											<tr><td>${emptyWorker.idWorker}</td>
												<td>${emptyWorker.firstName}</td>
												<td>${emptyWorker.secondName}</td>
												<td>${emptyWorker.profession}</td>
												<td><form action="controller" method="post">
														<input type="hidden" name="workerId" value="${emptyWorker.idWorker}">
														<input type="hidden" name="brigadId" value="${brigadsNum.idBrigads}">
														<input type="hidden" name="command" value="addToBrigad">
														<input type="submit" value="<fmt:message key='Add_to_brigad'/>">
													</form>
												</td>
											</tr>
										</c:forEach>
									</table>
								</li>
							</ul>	
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${empty workers.getEmptyWorker()}">
				<button disabled><fmt:message key='No_free_worker'/></button>
			</c:if>
		</li>
	</ul>
</c:forEach> 

<script>
	function show(state){
		document.getElementById('window').style.display = state;	
		document.getElementById('gray').style.display = state; 		
	}	
</script>  
</body>
</html>