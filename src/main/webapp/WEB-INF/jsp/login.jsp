<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <c:import url="header.jsp"/><br/>
    <title>Login Page</title>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="en_US"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:otherwise>
</c:choose>

<c:out value="${sessionScope.regSuccess}"/>

<form name="loginForm" action="app" method="POST">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="register.login" bundle="${lang}"/>:<br/>
    <input type="text" name="login" value="${sessionScope.account.login}"/><br/>
    <fmt:message key="register.password" bundle="${lang}"/>:<br/>
    <input type="password" name="password" value=""/><br/>
    <input type="submit" value="Log In"/>
</form>
${sessionScope.noAccount}


</body>
</html>
