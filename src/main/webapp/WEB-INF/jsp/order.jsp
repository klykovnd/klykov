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
    <!--Card StyleSheet-->
    <link type="text/css" rel="stylesheet" href="css/card_dummy.css"/>
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
        <div class="demo">
            <form class="payment-card" action="app" method="post">
                <input type="hidden" name="command" value="order"/>
                <input type="hidden" name="expoId" value="${sessionScope.exposition.id}"/>
                <input type="number" name="number" min="1" value="1"/>
                <div class="bank-card">
                    <div class="bank-card__side bank-card__side_front">
                        <div class="bank-card__inner">
                            <label class="bank-card__label bank-card__label_holder">
                                <span class="bank-card__hint">Holder of card</span>
                                <input type="text" class="bank-card__field" placeholder="Holder of card"
                                       pattern="[A-Za-z, ]{2,}" name="cardHolder" required>
                            </label>
                        </div>
                        <div class="bank-card__inner">
                            <label class="bank-card__label bank-card__label_number">
                                <span class="bank-card__hint">Number of card</span>
                                <input type="text" class="bank-card__field" placeholder="Number of card"
                                       pattern="[0-9]{16}"
                                       name="cardNumber" required>
                            </label>
                        </div>
                        <div class="bank-card__inner">
                            <span class="bank-card__caption">valid thru</span>
                        </div>
                        <div class="bank-card__inner bank-card__footer">
                            <label class="bank-card__label bank-card__month">
                                <span class="bank-card__hint">Month</span>
                                <input type="text" class="bank-card__field" placeholder="MM" maxlength="2"
                                       pattern="[0-9]{2}" name="month" required>
                            </label>
                            <span class="bank-card__separator">/</span>
                            <label class="bank-card__label bank-card__year">
                                <span class="bank-card__hint">Year</span>
                                <input type="text" class="bank-card__field" placeholder="YY" maxlength="2"
                                       pattern="[0-9]{2}" name="year" required>
                            </label>
                        </div>
                    </div>
                    <div class="bank-card__side bank-card__side_back">
                        <div class="bank-card__inner">
                            <label class="bank-card__label bank-card__cvc">
                                <span class="bank-card__hint">CVV</span>
                                <input type="text" class="bank-card__field" placeholder="CVV" maxlength="3"
                                       pattern="[0-9]{3}" name="cvv" required>
                            </label>
                        </div>
                    </div>
                </div>

                <button class="btn waves-effect waves-light pink darken-4" type="submit" name="action">
                    <fmt:message key="main.buy" bundle="${lang}"/>
                    <i class="material-icons right">add_shopping_cart</i>
                </button>

            </form>
        </div>
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
