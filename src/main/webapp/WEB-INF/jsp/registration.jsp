<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <c:import url="header.jsp"/><br/>
    <title>Registration</title>
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

<form name="regForm" action="app" method="POST">
    <input type="hidden" name="command" value="registration"/>
    <fmt:message key="register.firstname" bundle="${lang}"/>:<br/>
    <input type="text" name="lastName" value="" required/><br/>
    <fmt:message key="register.lastname" bundle="${lang}"/>:<br/>
    <input type="text" name="firstName" value="" required/><br/>
    <fmt:message key="register.login" bundle="${lang}"/>:<br/>
    <input type="text" name="login" value="" required/><br/>
    <fmt:message key="register.password" bundle="${lang}"/><br/>
    <input type="password" name="password" value="" required/><br/>
    <fmt:message key="register.repeat" bundle="${lang}"/><br/>
    <input type="password" name="repeat" value="" required/><br/>
    <fmt:message key="register.email" bundle="${lang}"/><br/>
    <input type="email" name="email" value="" required/><br/>

    <input type="submit" value="Registration"/>
    <br/>
    ${sessionScope.regFail}
</form>
</body>
</html>
