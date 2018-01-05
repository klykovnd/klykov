<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
      integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"
        integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4"
        crossorigin="anonymous"></script>


<c:choose>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="en_US"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:otherwise>
</c:choose>


<fmt:setBundle basename="pagecontent" var="lang" scope="session"/>


<div style="height: 80px; padding: 5px;">
    <div style="float: left">
        <a href="/main"><h1><fmt:message key="main.title" bundle="${lang}"/></h1></a>
    </div>
    <div style="float: right" align="right">
        <form name="changeLocale" action="app" method="post">
            <input type="hidden" name="command" value="localization">
            <input type="hidden" name="page" value="${pageContext.request.servletPath}">
            <select id="locale" name="locale" onchange="submit()">
                <option value="en_US" ${sessionScope.locale == "en_US" ? "selected" : ""}>English</option>
                <option value="ru_RU" ${sessionScope.locale == "ru_RU" ? "selected" : ""}>Русский</option>
            </select>
        </form>
        <br/>

        <div style="float: right">
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <fmt:message key="main.hello" bundle="${lang}"/>, ${sessionScope.account.firstName}<br/><a
                        href="app?command=logout"><fmt:message key="main.exit" bundle="${lang}"/></a>
                </c:when>
                <c:otherwise>
                    <a href="/login"><fmt:message key="register.signIn" bundle="${lang}"/></a>
                    <fmt:message key="register.or" bundle="${lang}"/>
                    <a href="/registration"><fmt:message key="register.signUp" bundle="${lang}"/></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>


<hr>

