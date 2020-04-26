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
<title>Add Worker</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<c:if test="${administratorId == null}">
	 <c:redirect url="MainPage.jsp" />
</c:if>

<h2 align = "center"><fmt:message key='Information_about_worker'/></h2>
		<div class = "justified">
       		<form action="controller" method="post">
				<fieldset>
					<legend><fmt:message key='First_name'/></legend>
					<input name="firstName"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Second_name'/></legend>
					<input name="secondName"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Profession'/></legend>
					<input name="profession"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Phone_number'/></legend>
					<input name="phoneNumber"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Email'/></legend>
					<input name="email"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Access_level'/></legend>
					<select name="status">
						<option value="Сотрудник"><fmt:message key='Worker'/></option>
						<option value="Диспетчер"><fmt:message key='Dispatcher'/></option>
						<option value="Администратор"><fmt:message key='Administrator'/></option>
					</select>
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
					<input type="hidden" name="command" value="addWorker">
					<input type="submit" value="<fmt:message key='Add'/>">
				</div>
			</form><br>
    	</div>
</body>
</html>