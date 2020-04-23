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
<body>
<c:if test="${passportId != null or workerId != null or administratorId != null}">
	<jsp:forward page="MainPage.jsp"></jsp:forward>
</c:if>
<nav class="two">
	<ul>
		<li>
			<a href="MainPage.jsp"><fmt:message key='Main'/> <fmt:message key='page'/></a>
		</li>
		<c:if test="${passportId == null}">
			<li>
				<a href="Login.jsp"><fmt:message key='Sign_In'/></a>
			</li>
			<li>
				<a href="Registration.jsp"><fmt:message key='Registration'/></a>
			</li>
		</c:if>
		<c:if test="${passportId != null}">
			<li>
				<form action="controller" method="post">
					<input type="hidden" name="command" value="goToCabinet">
					<input type="submit" value="<fmt:message key='Cabinet'/>" class="submit1">
				</form>
			</li>
		</c:if>
			<li style="float: right;">
				<form action="controller" method="post">
					<input type="hidden" name="language" value="ru">
					<input type="hidden" name="command" value="language">
					<input type="hidden" name="page" value="Registration.jsp">
					<input type="submit" value="RU">  
				</form>
			</li>
			<li style="float: right;">
				<form action="controller" method="post">
					<input type="hidden" name="language" value="en">
					<input type="hidden" name="command" value="language">
					<input type="hidden" name="page" value="Registration.jsp">
					<input type="submit" value="EN">  
				</form>
			</li>
	</ul>
</nav>

<h2 align = "center"><fmt:message key='Registration'/></h2>
		<div class = "justified">
       		<form action="controller" method="post">
       			<fieldset>
					<legend><fmt:message key='Passport_number'/></legend>
					<input name="passportNumber"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='First_name'/></legend>
					<input name="firstName"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Second_name'/></legend>
					<input name="secondName"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Email'/></legend>
					<input name="email"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Login'/></legend>
					<input name="login"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Password'/></legend>
					<input type="password" name="password"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Confirm'/> <fmt:message key='password'/></legend>
					<input type="password" name="confirmPassword"><br>
				</fieldset>
				<div align="center">
					<input type="submit" value="<fmt:message key='Registration'/>">
					<input type="hidden" name="command" value="registration">
				</div>
			</form><br>
    	</div>
</body>
</html>