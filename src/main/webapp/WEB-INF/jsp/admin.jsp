<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
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


<jsp:useBean id="today" class="java.util.Date"/>
<fmt:formatDate value="${today}" var="now" pattern="yyyy-MM-dd"/>


<div class="container">

    <h5>Добавить новую выставку</h5>
    <hr>
    <div class="row">
        <form class="col s6" action="app" method="post">
            <input type="hidden" name="command" value="creation">
            <div class="row">
                <div class="input-field col s6">
                    <i class="material-icons prefix">date_range</i>
                    <label for="dateFrom"><fmt:message key="main.from" bundle="${lang}"/></label>
                    <input id="dateFrom" type="text" class="datepicker validate" placeholder="Date From" name="dateFrom"
                           min="${now}"
                           value="${now}" required>
                </div>
                <div class="input-field col s6">
                    <i class="material-icons prefix">date_range</i>
                    <label for="dateTo"><fmt:message key="main.to" bundle="${lang}"/></label>
                    <input id="dateTo" type="text" class="datepicker" placeholder="Date To" name="dateTo"
                           min="${now}"
                           value="${now}" required>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s6">
                    <i class="material-icons prefix">title</i>
                    <input id="icon_prefix1" type="text" class="validate" name="title" value="" required>
                    <label for="icon_prefix1"><fmt:message key="order.title" bundle="${lang}"/></label>
                </div>

                <div class="input-field col s6">
                    <i class="material-icons prefix">attach_money</i>
                    <input id="price1" type="number" name="price" step=0.1 min="1" value="1" required/>
                    <label for="price1"><fmt:message key="order.price" bundle="${lang}"/></label>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s6">
                    <i class="material-icons prefix">color_lens</i>
                    <select name="theme" class="validate">
                        <c:forEach var="theme" items="${sessionScope.themes}">
                            <option value="${theme}">${theme}</option>
                        </c:forEach>
                    </select>
                    <label><fmt:message key="main.theme" bundle="${lang}"/></label>
                </div>


                <div class="input-field col s6">
                    <i class="material-icons prefix">account_balance</i>
                    <select name="hallId">
                        <c:forEach var="hall" items="${sessionScope.halls}">
                            <option value="${hall.id}"> ${hall.name}</option>
                        </c:forEach>
                    </select>
                    <label><fmt:message key="main.expohall" bundle="${lang}"/></label>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s6">
                    <i class="material-icons prefix">access_time</i>
                    <label for="time1"><fmt:message key="main.begintime" bundle="${lang}"/></label>
                    <input id="time1" type="text" class="timepicker" name="beginTime" required>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">link</i>
                    <input id="icon_prefix2" type="url" class="validate" name="url" required>
                    <label for="icon_prefix2">URL</label>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s12">
                    <i class="material-icons prefix">description</i>
                    <textarea name="description" id="textarea" class="materialize-textarea" required></textarea>
                    <label for="textarea"><fmt:message key="main.description" bundle="${lang}"/></label>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s6">
                    <button class="btn waves-effect waves-light pink darken-4" type="submit">
                        <fmt:message key="admin.exposition.create" bundle="${lang}"/>
                        <i class="material-icons right">add_box</i>
                    </button>
                </div>
            </div>
        </form>
    </div>


    <br/>
    <br/>

    <!--SELECT UPDATE-->
    <h5>Редактировать выставку</h5>
    <hr>

    <div class="row">
        <form class="col s6" action="app" method="get">
            <input type="hidden" name="command" value="showExposition">
            <select name="expositionId" onchange="submit()">
                <c:forEach var="exposition" items="${sessionScope.allExpositions}">
                    <option value="${exposition.id}" ${exposition.id == sessionScope.updExposition.id ? "selected" : ""}> ${exposition.title}</option>
                </c:forEach>
            </select>
        </form>
    </div>


    <!--UPDATE FORM-->
    <c:if test="${sessionScope.updExposition!=null}">
        <div class="row">
            <form class="col s6" action="app" method="post">
                <input type="hidden" name="command" value="update">
                <input type="hidden" name="expositionId" value="${sessionScope.updExposition.id}"/>
                <div class="row">

                    <div class="input-field col s6">
                        <i class="material-icons prefix">date_range</i>
                        <label for="dateFrom1"><fmt:message key="main.from" bundle="${lang}"/></label>
                        <input id="dateFrom1" type="text" class="datepicker" placeholder="Date From" name="dateFrom"
                               min="${now}"
                               value="${sessionScope.updExposition.dateFrom}" required>
                    </div>
                    <div class="input-field col s6">
                        <i class="material-icons prefix">date_range</i>
                        <label for="dateTo1"><fmt:message key="main.to" bundle="${lang}"/></label>
                        <input id="dateTo1" type="text" class="datepicker" placeholder="Date To" name="dateTo"
                               min="${now}"
                               value="${sessionScope.updExposition.dateTo}" required>
                    </div>
                </div>


                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">title</i>
                        <input id="icon_prefix3" type="text" class="validate" name="title"
                               value="${sessionScope.updExposition.title}" required>
                        <label for="icon_prefix3"><fmt:message key="order.title" bundle="${lang}"/></label>
                    </div>

                    <div class="input-field col s6">
                        <i class="material-icons prefix">attach_money</i>
                        <input id="price2" type="number" name="price" step=0.1 min="1"
                               value="${sessionScope.updExposition.ticketPrice}" required/>
                        <label for="price2"><fmt:message key="order.price" bundle="${lang}"/></label>
                    </div>
                </div>


                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">color_lens</i>
                        <input id="icon_prefix5" type="text" class="validate" name="theme"
                               value="${sessionScope.updExposition.theme}">
                        <label for="icon_prefix5"><fmt:message key="main.theme" bundle="${lang}"/></label>
                    </div>

                    <div class="input-field col s6">
                        <i class="material-icons prefix">account_balance</i>
                        <select name="hallId">
                            <c:forEach var="hall" items="${sessionScope.halls}">
                                <option value="${hall.id}" ${hall.id == sessionScope.updExposition.expoHallId ? "selected" :""}>${hall.name}</option>
                            </c:forEach>
                        </select>
                        <label><fmt:message key="main.expohall" bundle="${lang}"/></label>
                    </div>
                </div>


                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">access_time</i>
                        <label for="time2"><fmt:message key="main.begintime" bundle="${lang}"/></label>
                        <input id="time2" type="text" class="timepicker" name="beginTime"
                               value="${sessionScope.updExposition.beginTime}" required>
                    </div>
                </div>


                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">link</i>
                        <input id="icon_prefix4" type="url" class="validate" name="url"
                               value="${sessionScope.updExposition.picture}" required>
                        <label for="icon_prefix4">URL</label>
                    </div>
                </div>


                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">description</i>
                        <textarea name="description" id="textarea1"
                                  class="materialize-textarea" required>${sessionScope.updExposition.description}</textarea>
                        <label for="textarea1"><fmt:message key="main.description" bundle="${lang}"/></label>
                    </div>
                </div>


                <div class="row">
                    <div class="input-field col s6">
                        <button class="btn waves-effect waves-light pink darken-4" type="submit">
                            <fmt:message key="admin.exposition.update" bundle="${lang}"/>
                            <i class="material-icons right">update</i>
                        </button>
                    </div>

                    <div class="input-field col s6">
                        <form action="app" method="post">
                            <input type="hidden" name="command" value="delete">
                            <input type="hidden" name="expoId" value="${sessionScope.updExposition.id}"/>
                            <button class="btn waves-effect waves-light pink darken-4" type="submit">
                                <fmt:message key="admin.exposition.delete" bundle="${lang}"/>
                                <i class="material-icons right">delete</i>
                            </button>
                        </form>
                    </div>
                </div>
            </form>
        </div>
    </c:if>
</div>


<br/>
<c:import url="parts/footer.jsp"/>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</body>
</html>
