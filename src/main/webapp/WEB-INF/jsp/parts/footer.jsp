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

<footer class="page-footer indigo darken-3">
    <div class="container">
        <div class="row">
            <div class="col s2 offset-s10">
                <form name="changeLocale" action="app" method="post">
                    <input type="hidden" name="command" value="localization">
                    <input type="hidden" name="page" value="${pageContext.request.servletPath}">
                    <select name="locale" onchange="submit()">
                        <option value="en_US" ${sessionScope.locale == "en_US" ? "selected" : ""}>English</option>
                        <option value="ru_RU" ${sessionScope.locale == "ru_RU" ? "selected" : ""}>Русский</option>
                    </select>
                </form>
            </div>
        </div>
    </div>
    <div class="footer-copyright indigo darken-3">
        <div class="container">
            © 2018 All Rights Reserved
            <a class="grey-text text-lighten-4 right">E-mail: klykovnd@gmail.com</a>
        </div>
    </div>
</footer>
