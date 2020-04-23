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
<title>Update Worker</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2 align = "center"><fmt:message key='Information_about_worker'/></h2>
		<div class = "justified">
       		<form action="controller" method="post">
				<fieldset>
					<legend><fmt:message key='First_name'/></legend>
					<input name="firstName" value="${worker.firstName}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Second_name'/></legend>
					<input name="secondName" value="${worker.secondName}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Profession'/></legend>
					<input name="profession" value="${worker.profession}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Phone_number'/></legend>
					<input name="phoneNumber" value="${worker.phoneNumber}"><br>
				</fieldset>
				<fieldset>
					<legend><fmt:message key='Email'/></legend>
					<input name="email" value="${worker.email}"><br>
				</fieldset>
				<div align="center">
					<input type="hidden" name="workerId" value="${worker.idWorker}">
					<input type="hidden" name="command" value="updateWorker">
					<input type="submit" value="<fmt:message key='Update'/>">
				</div>
			</form><br>
    	</div>
</body>
</html>