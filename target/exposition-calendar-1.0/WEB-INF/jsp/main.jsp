<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <c:import url="header.jsp"/><br/>
    <title>
        <fmt:message key="main.title" bundle="${lang}"/>
    </title>
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

<fmt:setBundle basename="pagecontent" var="lang"/>
<jsp:useBean id="today" class="java.util.Date"/>
<fmt:formatDate value="${today}" var="now" pattern="yyyy-MM-dd"/>


<div class="container-fluid">
    <div class="row">
        <div class="col-2">
            <div class="card">
                <div class="card-body">
                    <form name="selection" action="app" method="post">
                        <input type="hidden" name="command" value="selection">
                        <fmt:message key="main.from" bundle="${lang}"/>&nbsp;<br/>
                        <input class="input-group date" type="date" name="dateFrom"
                               value="${sessionScope.dateFrom}">&nbsp;<br/>
                        <fmt:message key="main.to" bundle="${lang}"/>&nbsp;<br/>
                        <input class="input-group date" type="date" name="dateTo" min="${now}"
                               value="${sessionScope.dateTo}">&nbsp;<br/>
                        <fmt:message key="main.theme" bundle="${lang}"/>&nbsp;<br/>
                        <select class="custom-select" name="theme" data-selected="All">
                            <option value="all"><fmt:message key="main.all" bundle="${lang}"/></option>
                            <c:forEach var="theme" items="${sessionScope.themes}">
                                <option value="${theme}" ${theme == sessionScope.theme ? "selected" : ""}> ${theme}</option>
                            </c:forEach>
                        </select>&nbsp;<br/>
                        <fmt:message key="main.expohall" bundle="${lang}"/>&nbsp;<br/>
                        <select class="custom-select" name="hallId">
                            <option value=""><fmt:message key="main.all" bundle="${lang}"/></option>
                            <c:forEach var="hall" items="${sessionScope.halls}">
                                <option value="${hall.id}" ${hall.id == sessionScope.hallId ? "selected" :""}> ${hall.name}</option>
                            </c:forEach>
                        </select>&nbsp;<br/>
                        <input type="submit" class="btn btn-primary" value="Search Expositions">
                    </form>
                </div>
            </div>
        </div>
        <div class="col-3">
            <c:forEach items="${sessionScope.expositions}" var="exposition" varStatus="count">
                <div class="card">
                    <div class="view overlay hm-white-slight">

                        <img src="https://mdbootstrap.com/img/Photos/Horizontal/Nature/4-col/img%20%287%29.jpg"
                             class="img-fluid" alt="">
                        <a href="#">
                            <div class="mask"></div>
                        </a>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title"><c:out value="${exposition.name}"/></h4> <br>
                        <p class="card-text">
                            <fmt:formatDate type="date" value="${exposition.dateFrom}"/> -
                            <fmt:formatDate type="date" value="${exposition.dateTo}"/> <br/>
                            <fmt:message key="main.theme" bundle="${lang}"/>:&nbsp <c:out
                                value="${exposition.theme}"/><br/>
                                ${sessionScope.halls[exposition.expoHallId - 1].name} <br/>
                        </p>
                        <c:if test="${exposition.dateTo gt now}">
                            <form action="order" method="post">
                                <input type="hidden" name="command" value="selection">
                                <input type="hidden" name="expositionId" value="${exposition.id}">
                                <input type="submit" class="btn btn-success" value="Buy">
                            </form>
                        </c:if><br/>
                    </div>
                </div>
                <br/>
            </c:forEach>
            ${requestScope.nothingFound}
        </div>
        <div class="col-3">
            <!--something else-->
        </div>
    </div>
</div>


<c:import url="footer.jsp"/><br/>

</body>
</html>
