<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta title="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Expositions Calendar</title>
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
<jsp:useBean id="today" class="java.util.Date"/>
<fmt:formatDate value="${today}" var="now" pattern="yyyy-MM-dd"/>
<fmt:setBundle basename="pagecontent" var="lang"/>
<c:import url="parts/header.jsp"/><br/>

<!--Search form here -->
<div class="container">
    <div class="row">
        <form class="col s12" action="main" method="post">
            <input type="hidden" name="command" value="selection">
            <div class="input-field col s2">
                <i class="material-icons prefix">date_range</i>
                <label for="dateFrom"><fmt:message key="main.from" bundle="${lang}"/></label>
                <input id="dateFrom" type="text" class="datepicker" placeholder="Date From" name="dateFrom" min="${now}"
                       value="${sessionScope.dateFrom}">
            </div>
            <div class="input-field col s2">
                <i class="material-icons prefix">date_range</i>
                <label for="dateTo"><fmt:message key="main.to" bundle="${lang}"/></label>
                <input id="dateTo" type="text" class="datepicker" placeholder="Date To" name="dateTo" min="${now}"
                       value="${sessionScope.dateTo}">
            </div>
            <div class="input-field col s3">
                <i class="material-icons prefix">color_lens</i>
                <select name="theme">
                    <option value="all" data-selected="all"><fmt:message key="main.all" bundle="${lang}"/></option>
                    <c:forEach var="theme" items="${sessionScope.themes}">
                        <option value="${theme}" ${theme == sessionScope.theme ? "selected" : ""}> ${theme}</option>
                    </c:forEach>
                </select>
                <label><fmt:message key="main.theme" bundle="${lang}"/></label>
            </div>
            <div class="input-field col s3">
                <i class="material-icons prefix">account_balance</i>

                <select name="hallId">
                    <option value=""><fmt:message key="main.all" bundle="${lang}"/></option>
                    <c:forEach var="hall" items="${sessionScope.halls}">
                        <option value="${hall.id}" ${hall.id == sessionScope.hallId ? "selected" :""}> ${hall.name}</option>
                    </c:forEach>
                </select>
                <label><fmt:message key="main.expohall" bundle="${lang}"/></label>
            </div>

            <div class="input-field col s2">
                <button class="btn waves-effect waves-light pink darken-4" type="submit"><fmt:message key="main.search"
                                                                                                      bundle="${lang}"/>
                    <i class="material-icons right">search</i>
                </button>
            </div>
        </form>
    </div>




    <!--Expositions Cards here-->
    <div class="row">
        <c:if test="${not empty sessionScope.nothingFound}">
            <fmt:message key="message.nothingFound" bundle="${lang}"/>
        </c:if>
        <c:forEach items="${sessionScope.expositions}" var="exposition">
            <div class="col s4">
                <div class="card medium">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img class="activator" src="${exposition.picture}">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4"><b> <c:out value="${exposition.title}"/></b> <i class="material-icons right">more_vert</i></span>
                        <p><fmt:formatDate type="date" value="${exposition.dateFrom}"/> -
                            <fmt:formatDate type="date" value="${exposition.dateTo}"/></p>
                    </div>
                    <div class="card-action">
                        <!--Order Page link-->
                        <c:if test="${exposition.dateTo gt now}">
                            <form action="order" method="post">
                                <input type="hidden" name="expositionId" value="${exposition.id}">
                                <button class="btn waves-effect waves-light pink darken-4" type="submit">
                                    <fmt:message key="main.buy" bundle="${lang}"/>
                                    <i class="material-icons right">add_shopping_cart</i>
                                </button>
                            </form>
                        </c:if>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4"> <b>${exposition.title} </b><i
                                class="material-icons right">close</i></span>
                        <p> <fmt:formatDate type="date" value="${exposition.dateFrom}"/> -
                            <fmt:formatDate type="date" value="${exposition.dateTo}"/></p>

                        <fmt:formatDate value="${exposition.beginTime}" var="time" pattern="HH:mm"/>
                        <p><fmt:message key="main.begin" bundle="${lang}"/> <c:out value="${time}"/></p>

                        <p><b> <fmt:message key="main.theme" bundle="${lang}"/>:</b> <c:out value="${exposition.theme}"/></p>

                        <c:forEach var="hall" items="${sessionScope.halls}">
                            <c:if test="${hall.id == exposition.expoHallId}">
                               <b><fmt:message key="main.expohall" bundle="${lang}"/>:</b> <c:out value="${hall.name}"/> <br>
                        <i><c:out value="${hall.address}"/></i>
                            </c:if>
                        </c:forEach>

                        <p><b><fmt:message key="exposition.description" bundle="${lang}"/>:</b>
                         <c:out value="${exposition.description}"/>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>


    <div class="row">

        <ul class="pagination">


            <c:if test="${sessionScope.currentPage != 1}">
                <li class="waves-effect"><a
                        href="app?command=selection&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&theme=${sessionScope.theme}&hallId=${sessionScope.hallId}&page=${sessionScope.currentPage - 1}"><i
                        class="material-icons">chevron_left</i></a></li>
            </c:if>


            <c:forEach begin="1" end="${sessionScope.numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${sessionScope.currentPage eq i}">
                        <li class="active"><a
                                href="app?command=selection&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&theme=${sessionScope.theme}&hallId=${sessionScope.hallId}&page=${i}">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="waves-effect"><a
                                href="app?command=selection&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&theme=${sessionScope.theme}&hallId=${sessionScope.hallId}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <c:if test="${sessionScope.currentPage lt sessionScope.numberOfPages}">
                <li class="waves-effect"><a
                        href="app?command=selection&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&theme=${sessionScope.theme}&hallId=${sessionScope.hallId}&page=${sessionScope.currentPage + 1}"><i
                        class="material-icons">chevron_right</i></a></li>
            </c:if>

        </ul>
    </div>
</div>


<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<c:import url="parts/footer.jsp"/>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</body>
</html>
