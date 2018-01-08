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
        <form class="col s12" action="app" method="post">
            <input type="hidden" name="command" value="registration"/>
            <div class="row">
                <div class="input-field col s3 offset-s3">
                    <i class="material-icons prefix">person</i>
                    <input id="icon_prefix1" type="text" class="validate" name="firstName" value="" required>
                    <label for="icon_prefix1"><fmt:message key="register.firstname" bundle="${lang}"/></label>
                </div>

                <div class="input-field col s3">
                    <i class="material-icons prefix">person</i>
                    <input id="icon_prefix2" type="text" class="validate" name="lastName" value="" required>
                    <label for="icon_prefix2"><fmt:message key="register.lastname" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="icon_prefix" type="text" class="validate" name="login" value="" required>
                    <label for="icon_prefix"><fmt:message key="register.login" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">lock</i>
                    <input id="icon_lock1" class="validate" type="password" name="password" value="" required>
                    <label for="icon_lock1"><fmt:message key="register.password" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">lock</i>
                    <input id="icon_lock2" class="validate" type="password" name="repeat" value="" required>
                    <label for="icon_lock2"><fmt:message key="register.repeat" bundle="${lang}"/></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">mail</i>
                    <input id="icon_mail" class="validate" type="email" name="email" value="" required>
                    <label for="icon_mail"><fmt:message key="register.email" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <button class="btn waves-effect waves-light pink darken-4" type="submit" name="action"><fmt:message
                            key="register.sign" bundle="${lang}"/>
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
    ${sessionScope.regFail}
</div>
<br/>
<c:import url="parts/footer.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</body>
</html>
