<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

	<fmt:setLocale value="${language}"/>
	<fmt:setBundle basename='property'/> 

<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
<link href="resources/style/style1.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style2.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style4.css" rel="stylesheet" type="text/css"/>
<link href="resources/style/style5.css" rel="stylesheet" type="text/css"/>
</head>	
<body>
<div class="error">
		<h2><fmt:message key="Message"/></h2>
		<h3><fmt:message key="${requestScope.errorMessage}"/></h3>
		<a href="javascript:history.back()" class="a"><fmt:message key="Back"/></a>
</div>
</body>
</html>