<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<c:choose>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="en_US"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="pagecontent" var="lang" scope="session"/>

<nav>
    <div class="nav-wrapper indigo darken-3">
        <a href="main" class="brand-logo"><fmt:message key="main.title" bundle="${lang}"/></a>
        <ul id="nav" class="right hide-on-med-and-down">
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <li><fmt:message key="main.hello" bundle="${lang}"/>, <c:out
                            value="${sessionScope.account.firstName}"/></li>
                    <!-- Dropdown Trigger -->
                    <li><a class='dropdown-button btn pink darken-4' href='#' data-activates='dropdown1'><i
                            class="material-icons">account_circle</i></a></li>
                    <!-- Dropdown Structure -->
                    <ul id='dropdown1' class='dropdown-content'>
                        <li><a href="account"><i class="material-icons">edit</i><fmt:message key="main.account" bundle="${lang}"/></a></li>

                        <c:if test="${sessionScope.account.role eq 'admin'}">
                            <li><a href="admin"><i class="material-icons">build</i><fmt:message key="main.admin" bundle="${lang}"/></a></li>
                        </c:if>

                        <li class="divider"></li>

                        <li><a href="app?command=logout"><i class="material-icons">exit_to_app</i><fmt:message key="main.exit" bundle="${lang}"/></a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <li><a href="login"><fmt:message key="account.signIn" bundle="${lang}"/></a></li>
                    <li><fmt:message key="register.or" bundle="${lang}"/></li>
                    <li><a href="registration"><fmt:message key="account.signUp" bundle="${lang}"/></a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
