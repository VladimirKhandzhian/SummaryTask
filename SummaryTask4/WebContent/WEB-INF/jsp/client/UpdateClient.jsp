<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Worker</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<c:if test="${passportId == null}">
	 <c:redirect url="MainPage.jsp" />
</c:if>

<h2 align = "center">Личные данные</h2>
		<div class = "justified">
       		<form action="controller" method="post">
       			<fieldset>
					<legend>Серия пасспорта</legend>
					<input name="newPassportId" value="${passenger.passportId}"><br>
				</fieldset>
				<fieldset>
					<legend>Имя</legend>
					<input name="firstName" value="${passenger.firstName}"><br>
				</fieldset>
				<fieldset>
					<legend>Фамилия</legend>
					<input name="secondName" value="${passenger.secondName}"><br>
				</fieldset>
				<fieldset>
					<legend>Электронная почта</legend>
					<input name="email" value="${passenger.email}"><br>
				</fieldset>
				<div align="center">
					<input type="hidden" name="command" value="updateClient">
					<input type="submit" value="Обновить">
				</div>
			</form><br>
    	</div>
</body>
</html>