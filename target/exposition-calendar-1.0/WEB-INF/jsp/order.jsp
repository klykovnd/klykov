<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Exposition Calendar</title>
</head>


<body class="grey lighten-2">
<c:import url="parts/header.jsp"/><br/>

<div class="container grey lighten-2">

    <table class="bordered">
        <tr>
            <th><fmt:message key="order.date" bundle="${lang}"/></th>
            <th><fmt:message key="order.title" bundle="${lang}"/></th>
            <th><fmt:message key="order.price" bundle="${lang}"/></th>
        </tr>
        <tr>
            <td><fmt:formatDate type="date" value="${sessionScope.exposition.dateFrom}"/> - <fmt:formatDate type="date"
                                                                                                            value="${sessionScope.exposition.dateTo}"/></td>
            <td><c:out value="${sessionScope.exposition.title}"/></td>
            <td><c:out value="${sessionScope.exposition.ticketPrice}"/></td>
        </tr>
    </table>

</div>

<br/>

<div class="container grey lighten-2">


    <div class="row">


        <form name="orderForm" action="app" method="post">
            <input type="hidden" name="command" value="order"/>
            <input type="hidden" name="expoId" value="${sessionScope.exposition.id}"/>
            <input type="number" name="number" min="1" value="1"/>
            <br/>

            <button class="btn waves-effect waves-light pink darken-4" type="submit" name="action">
                <fmt:message key="main.buy" bundle="${lang}"/>
                <i class="material-icons right">add_shopping_cart</i>
            </button>
        </form>
    </div>


</div>
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
