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
    <title>Order</title>
</head>

<body>
<c:import url="parts/header.jsp"/><br/>

<div class="container">
    <table class="bordered">
        <tr>
            <th><i class="material-icons prefix">date_range</i> <fmt:message key="order.date" bundle="${lang}"/></th>

            <th><i class="material-icons prefix">title</i><fmt:message key="order.title" bundle="${lang}"/></th>

            <th><i class="material-icons prefix">attach_money</i><fmt:message key="order.price" bundle="${lang}"/></th>
        </tr>
        <tr>
            <td><fmt:formatDate type="date" value="${sessionScope.exposition.dateFrom}"/> - <fmt:formatDate type="date" value="${sessionScope.exposition.dateTo}"/></td>
            <td><c:out value="${sessionScope.exposition.title}"/></td>
            <td><c:out value="${sessionScope.exposition.ticketPrice}"/> <fmt:message key="order.hrn" bundle="${lang}"/></td>
        </tr>
    </table>
    <div class="col s12 m7">
        <div class="card horizontal">
            <div class="card-image">
                <img src="${sessionScope.exposition.picture}">
            </div>
            <div class="card-stacked">
                <div class="card-content">
                    <fmt:formatDate value="${sessionScope.exposition.beginTime}" var="time" pattern="HH:mm"/>
                    <p><fmt:message key="main.begin" bundle="${lang}"/> <c:out value="${time}"/></p><br>
                    <p><b> <fmt:message key="main.theme" bundle="${lang}"/>:</b> <c:out
                            value="${sessionScope.exposition.theme}"/></p><br>
                    <c:forEach var="hall" items="${sessionScope.halls}">
                        <c:if test="${hall.id == sessionScope.exposition.expoHallId}">
                            <b><fmt:message key="main.expohall" bundle="${lang}"/>:</b> <c:out value="${hall.name}"/> <br>
                            <i><c:out value="${hall.address}"/></i><br><br>
                        </c:if>
                    </c:forEach>
                    <b><fmt:message key="exposition.description" bundle="${lang}"/>:</b>
                    <p><c:out value="${sessionScope.exposition.description}"/></p>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>


<div class="row">
    <c:if test="${not empty requestScope.orderSuccess}">
        <h6 class="center-align green-text text-darken-2"><fmt:message key="order.sum" bundle="${lang}"/> <c:out
                value="${requestScope.sum}"/> <fmt:message key="order.passed" bundle="${lang}"/></h6>
        <h6 class="center-align green-text text-darken-2"><fmt:message key="order.success" bundle="${lang}"/></h6>
    </c:if>
</div>

<div class="row">
    <c:if test="${not empty requestScope.orderFail}">
        <h6 class="center-align red-text text-darken-2"><fmt:message key="order.fail" bundle="${lang}"/></h6>
    </c:if>
</div>
<div class="row">
    <div class="demo">
        <form class="payment-card" action="order" method="post">
            <input type="hidden" name="command" value="order"/>
            <input type="hidden" name="expoId" value="${sessionScope.exposition.id}"/>


            <div class="row">
                <div class="col s3">
                    <fmt:message key="order.number" bundle="${lang}"/>
                    <input type="number" name="number" min="1" value="1"/>
                </div>
            </div>

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
