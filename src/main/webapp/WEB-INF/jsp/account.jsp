<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta title="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Card StyleSheet-->
    <link type="text/css" rel="stylesheet" href="css/card_dummy.css"/>
    <title>Account</title>
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


<c:import url="parts/header.jsp"/><br/>

<div class="container">

    <div class="row">
        <!--offset row-->
    </div>

    <div class="row">
        <form class="col s6" action="login" method="post">
            <input type="hidden" name="command" value="update"/>
            <input type="hidden" name="object" value="account">
            <input type="hidden" name="accountId" value="${sessionScope.account.id}"/>

            <div class="row">
                <div class="input-field col s6">
                    <i class="material-icons prefix">person</i>
                    <input id="icon_prefix1" type="text" class="validate" name="firstName"
                           value="${sessionScope.account.firstName}" required>
                    <label for="icon_prefix1"><fmt:message key="account.firstname" bundle="${lang}"/></label>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s6">
                    <i class="material-icons prefix">person</i>
                    <input id="icon_prefix2" type="text" class="validate" name="lastName"
                           value="${sessionScope.account.lastName}" required>
                    <label for="icon_prefix2"><fmt:message key="account.lastname" bundle="${lang}"/></label>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s6">
                    <i class="material-icons prefix">mail</i>
                    <input id="icon_mail" class="validate" type="email" name="email"
                           value="${sessionScope.account.email}" required>
                    <label for="icon_mail"><fmt:message key="account.email" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s8">
                    <button class="btn waves-effect waves-light pink darken-4" type="submit"><fmt:message
                            key="account.update" bundle="${lang}"/>
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </div>
        </form>

        <div class="col s6">
            <ul class="collection with-header">
                <li class="collection-header"><h4><fmt:message key="account.orders" bundle="${lang}"/></h4></li>
                <c:forEach items="${sessionScope.orders}" var="entry">
                    <li class="collection-item">


                        <div>

                            <b><fmt:message key="account.order.number" bundle="${lang}"/>:</b> <c:out value="${entry.key.orderKey}"/><br>
                            <b><fmt:message key="order.title" bundle="${lang}"/>:</b> <c:out value="${entry.value.title}"/><br>
                                <b><fmt:message key="order.date"  bundle="${lang}"/>:</b> <fmt:formatDate type="date" value="${entry.value.dateFrom}"/> - <fmt:formatDate type="date" value="${entry.value.dateTo}"/><br>
                                    <b><fmt:message key="order.number" bundle="${lang}"/></b> <c:out value="${entry.key.ticketsNumber}"/><br>
                            <br>
                            <br>

                            <c:choose>
                                <c:when test="${today lt entry.key.dateValid}">
                                    <form action="app" method="post">
                                        <input type="hidden" name="command" value="send">
                                        <input type="hidden" name="hallId" value="${entry.value.expoHallId}">
                                        <input type="hidden" name="orderKey" value="${entry.key.orderKey}">
                                        <input type="hidden" name="ticketsNumber" value="${entry.key.ticketsNumber}">
                                        <input type="hidden" name="dateValid" value="${entry.key.dateValid}">
                                        <div class="row">
                                            <div class="input-field col s8">
                                                <button class="btn waves-effect waves-light pink darken-4" type="submit"><fmt:message
                                                        key="account.send" bundle="${lang}"/>
                                                    <i class="material-icons right">send</i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <b class="red-text text-darken-2">  <fmt:message key="account.expired" bundle="${lang}"/></b>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<c:import url="parts/footer.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</body>
</html>