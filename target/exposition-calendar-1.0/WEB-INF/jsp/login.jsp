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
    <title>Title</title>
</head>
<body class="grey lighten-2">

<c:choose>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="en_US"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:otherwise>
</c:choose>

<c:import url="parts/header.jsp"/><br/>

<div class="container grey lighten-2">
    <div class="row">
        <!--offset row-->
    </div>
    <div class="row">
        <!--offset row-->
    </div>
    <div class="row">
        <!--offset row-->
    </div>

        <div class="row">
            <form action="app" method="post">
                <input type="hidden" name="command" value="login"/>
                <div class="row">
                    <div class="input-field col s6 offset-s3">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="icon_prefix" type="text" class="validate" name="login"
                               value="${sessionScope.account.login}">
                        <label for="icon_prefix"><fmt:message key="register.login" bundle="${lang}"/></label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s6 offset-s3">
                        <i class="material-icons prefix">lock</i>
                        <input id="icon_lock" class="validate" type="password" name="password" value="">
                        <label for="icon_lock"><fmt:message key="register.password" bundle="${lang}"/></label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s2 offset-s3">
                        <button class="btn waves-effect waves-light pink darken-4" type="submit" name="action">
                            <fmt:message
                                    key="main.login" bundle="${lang}"/>
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        ${sessionScope.noAccount}
</div>
<br/>
<br/>
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