<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<jsp:useBean id="today" class="java.util.Date"/>
<fmt:formatDate value="${today}" var="now" pattern="yyyy-MM-dd"/>
<jsp:forward page="app?command=selection"/>
</body>
</html>
