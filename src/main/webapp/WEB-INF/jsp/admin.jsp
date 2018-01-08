<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta title="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Administration</title>
</head>
<body>
<c:import url="parts/header.jsp"/><br/>

<c:choose>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="en_US"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:otherwise>
</c:choose>


<div class="container">
    <form name="creation" action="app" method="post">
        <input type="hidden" name="command" value="creation">
        <fmt:message key="main.from" bundle="${lang}"/>&nbsp;
        <input type="date" name="from" value="" required>&nbsp;
        <fmt:message key="main.to" bundle="${lang}"/>&nbsp;
        <input type="date" name="to" min="${now}" value="" required>&nbsp;
        <input type="text" name="title" value="" required>&nbsp;
        <fmt:message key="main.theme" bundle="${lang}"/>&nbsp;
        <select name="theme">
            <c:forEach var="theme" items="${sessionScope.themes}">
                <option value="${theme}" ${theme == sessionScope.theme ? "selected" : ""}> ${theme}</option>
            </c:forEach>
        </select>&nbsp;
        <fmt:message key="main.expohall" bundle="${lang}"/>&nbsp;
        <select name="hallId">
            <c:forEach var="hall" items="${sessionScope.halls}">
                <option value="${hall.id}" ${hall.id == sessionScope.hallId ? "selected" :""}> ${hall.name}</option>
            </c:forEach>
        </select>&nbsp;
        <input type="number" name="price" min="1" value="1"/>&nbsp;
        <input type="submit" value="Create">
</div>

</form>

<br/>
<c:import url="parts/footer.jsp"/>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</body>
</html>
