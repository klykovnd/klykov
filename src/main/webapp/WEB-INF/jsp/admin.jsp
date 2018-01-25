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
    <title>Admin</title>
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


    <div class="row">

        <div class="col s6">
            <h5><fmt:message key="admin.createExposiiton" bundle="${lang}"/></h5>
            <hr>
        </div>

        <div class="col s6">
            <h5><fmt:message key="admin.createHall" bundle="${lang}"/></h5>
            <hr>
        </div>

    </div>


    <div class="row">
        <form class="col s6" action="admin" method="post">
            <input type="hidden" name="command" value="creation">
            <input type="hidden" name="object" value="exposition">
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
                    <label for="time1"><fmt:message key="exposition.begintime" bundle="${lang}"/></label>
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
                    <label for="textarea"><fmt:message key="exposition.description" bundle="${lang}"/></label>
                </div>
            </div>


            <div class="row">
                <div class="input-field col s6">
                    <button class="btn waves-effect waves-light pink darken-4" type="submit">
                        <fmt:message key="admin.create" bundle="${lang}"/>
                        <i class="material-icons right">add_box</i>
                    </button>
                </div>
            </div>
        </form>


        <div class="row">
            <form class="col s6" action="admin" method="post">
                <input type="hidden" name="command" value="creation">
                <input type="hidden" name="object" value="hall">
                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">location_city</i>
                        <input id="icon_prefix6" type="text" class="validate" name="name" required>
                        <label for="icon_prefix6"><fmt:message key="expohall.name" bundle="${lang}"/></label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <i class="material-icons prefix">location_on</i>
                        <input id="icon_prefix7" type="text" class="validate" name="address" required>
                        <label for="icon_prefix7"><fmt:message key="expohall.address" bundle="${lang}"/></label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s6">
                        <button class="btn waves-effect waves-light pink darken-4" type="submit">
                            <fmt:message key="admin.create" bundle="${lang}"/>
                            <i class="material-icons right">add_box</i>
                        </button>
                    </div>
                </div>

            </form>
        </div>


    </div>


    <br/>
    <br/>

    <!--SELECT WHICH TO UPDATE-->
    <div class="row">

        <div class="col s6">
            <h5><fmt:message key="admin.updateExposition" bundle="${lang}"/></h5>
            <hr>
        </div>

        <div class="col s6">
            <h5><fmt:message key="admin.updateHall" bundle="${lang}"/></h5>
            <hr>
        </div>

    </div>


    <!--EXPOSITION-->
    <div class="row">
        <form class="col s6" action="admin" method="get">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="object" value="exposition">
            <select name="expositionId" class="validate" onchange="submit()">
                <c:forEach var="exposition" items="${sessionScope.allExpositions}">
                    <option value="${exposition.id}" ${exposition.id == sessionScope.updExposition.id ? "selected" : ""}> ${exposition.title}</option>
                </c:forEach>
            </select>
        </form>

        <!--EXPOHALL-->
        <form class="col s6" action="admin" method="get">
            <input type="hidden" name="command" value="show">
            <input type="hidden" name="object" value="hall">
            <select name="hallId" class="validate" onchange="submit()">
                <c:forEach var="hall" items="${sessionScope.halls}">
                    <option value="${hall.id}" ${hall.id == sessionScope.updHall.id ? "selected" : ""}> ${hall.name}</option>
                </c:forEach>
            </select>
        </form>


    </div>


    <!--UPDATE FORM-->
    <div class="row">
        <div class="col s12">
            <div class="col s6">
                <c:if test="${sessionScope.updExposition!=null}">

                    <form action="admin" method="post">
                        <input type="hidden" name="command" value="update">
                        <input type="hidden" name="object" value="exposition">
                        <input type="hidden" name="expositionId" value="${sessionScope.updExposition.id}"/>
                        <div class="row">

                            <div class="input-field col s6">
                                <i class="material-icons prefix">date_range</i>
                                <label for="dateFrom1"><fmt:message key="main.from" bundle="${lang}"/></label>
                                <input id="dateFrom1" type="text" class="datepicker" placeholder="Date From"
                                       name="dateFrom"
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
                                <label for="time2"><fmt:message key="exposition.begintime" bundle="${lang}"/></label>
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
                                          class="materialize-textarea"
                                          required>${sessionScope.updExposition.description}</textarea>
                                <label for="textarea1"><fmt:message key="exposition.description"
                                                                    bundle="${lang}"/></label>
                            </div>
                        </div>


                        <div class="row">
                            <div class="input-field col s6">
                                <button class="btn waves-effect waves-light pink darken-4" type="submit">
                                    <fmt:message key="admin.update" bundle="${lang}"/>
                                    <i class="material-icons right">update</i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <div class="row">
                        <div class="col s6">
                            <form action="app" method="post">
                                <input type="hidden" name="command" value="delete">
                                <input type="hidden" name="object" value="exposition">
                                <input type="hidden" name="expositionId" value="${sessionScope.updExposition.id}"/>
                                <button class="btn waves-effect waves-light pink darken-4" type="submit">
                                    <fmt:message key="admin.delete" bundle="${lang}"/>
                                    <i class="material-icons right">delete</i>
                                </button>
                            </form>
                        </div>
                    </div>


                </c:if>
            </div>

            <div class="col s6">
                <c:if test="${sessionScope.updHall!=null}">
                    <form action="admin" method="post">
                        <input type="hidden" name="command" value="update">
                        <input type="hidden" name="object" value="hall">
                        <input type="hidden" name="hallId" value="${sessionScope.updHall.id}"/>
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">location_city</i>
                                <input id="icon_prefix9" type="text" class="validate" name="name"
                                       value="${sessionScope.updHall.name}" required>
                                <label for="icon_prefix9"><fmt:message key="expohall.name" bundle="${lang}"/></label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">location_on</i>
                                <input id="icon_prefix8" type="text" class="validate" name="address"
                                       value="${sessionScope.updHall.address}" required>
                                <label for="icon_prefix8"><fmt:message key="expohall.address" bundle="${lang}"/></label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s6">
                                <button class="btn waves-effect waves-light pink darken-4" type="submit">
                                    <fmt:message key="admin.update" bundle="${lang}"/>
                                    <i class="material-icons right">update</i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <div class="row">
                        <div class="col s6">
                            <form action="app" method="post">
                                <input type="hidden" name="command" value="delete">
                                <input type="hidden" name="object" value="hall">
                                <input type="hidden" name="hallId" value="${sessionScope.updHall.id}"/>
                                <button class="btn waves-effect waves-light pink darken-4" type="submit">
                                    <fmt:message key="admin.delete" bundle="${lang}"/>
                                    <i class="material-icons right">delete</i>
                                </button>
                            </form>
                        </div>
                    </div>

                </c:if>
            </div>

        </div>
    </div>
</div>


<br/>
<c:import url="parts/footer.jsp"/>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</body>
</html>
