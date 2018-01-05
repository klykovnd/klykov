<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="/css/calendar_styles.css">
    <c:import url="header.jsp"/><br/>
    <title>Exposition Calendar</title>
</head>
<body>

    <table>
        <tr>
            <th><fmt:message key="order.date" bundle="${lang}"/></th>
            <th><fmt:message key="order.name" bundle="${lang}"/></th>
            <th><fmt:message key="order.price" bundle="${lang}"/></th>
        </tr>
        <tr>
            <td><fmt:formatDate type = "date" value="${sessionScope.exposition.dateFrom}"/> -
                <fmt:formatDate type = "date" value="${sessionScope.exposition.dateTo}"/></td>
            <td><c:out value="${sessionScope.exposition.name}"/></td>
            <td><c:out value="${sessionScope.exposition.ticketPrice}"/></td>
        </tr>
    </table>
    <br/>
    <form name="orderForm" action="app" method="POST">
        <input type="hidden" name="command" value="order"/>
        <input type="hidden" name="expoId" value="${sessionScope.exposition.id}"/>
        <input type="number" name="number" min="1" value="1"/>

        <c:if test="${empty sessionScope.account}">
            <input type="email" name="recipient" value="" required/>
        </c:if><br/>
        <br/>
        <input type="submit" value="Buy"><br/>
        <br/>
    </form>


</body>
</html>
