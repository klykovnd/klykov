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
    <!--Card StyleSheet-->
    <link type="text/css" rel="stylesheet" href="css/card_dummy.css"/>
    <title>Registration</title>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.locale}">
        <fmt:setLocale value="en_US"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:otherwise>
</c:choose>

<c:import url="parts/header.jsp"/><br/>

<div class="container">

    <div class="row">
        <!--offset row-->
    </div>

    <div class="row">
        <div>
            <c:if test="${not empty sessionScope.notEqualPasswords}">
                <h6 class="center-align red-text text-darken-2 "><fmt:message key="message.notEqual" bundle="${lang}"/></h6>
            </c:if>

            <c:if test="${not empty sessionScope.loginExists}">
                <h6 class="center-align red-text text-darken-2 "><fmt:message key="message.loginExists" bundle="${lang}"/></h6>
            </c:if>

            <c:if test="${not empty sessionScope.fillAllFields}">
                <h6 class="center-align red-text text-darken-2 "><fmt:message key="message.fillAllFields" bundle="${lang}"/></h6>
            </c:if>
        </div>

        <form class="col s12" action="registration" method="post">
            <input type="hidden" name="command" value="registration"/>
            <div class="row">
                <div class="input-field col s3 offset-s3">
                    <i class="material-icons prefix">person</i>
                    <input id="icon_prefix1" type="text" class="validate" name="firstName" value="${sessionScope.firstName}" required>
                    <label for="icon_prefix1"><fmt:message key="account.firstname" bundle="${lang}"/></label>
                </div>

                <div class="input-field col s3">
                    <i class="material-icons prefix">person</i>
                    <input id="icon_prefix2" type="text" class="validate" name="lastName" value="${sessionScope.lastName}" required>
                    <label for="icon_prefix2"><fmt:message key="account.lastname" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="icon_prefix" type="text" class="validate" name="login" value="${sessionScope.login}" required>
                    <label for="icon_prefix"><fmt:message key="account.login" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">lock</i>
                    <input id="icon_lock1" class="validate" type="password" name="password" value="" required>
                    <label for="icon_lock1"><fmt:message key="account.password" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">lock</i>
                    <input id="icon_lock2" class="validate" type="password" name="repeat" value="" required>
                    <label for="icon_lock2"><fmt:message key="account.repeat" bundle="${lang}"/></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <i class="material-icons prefix">mail</i>
                    <input id="icon_mail" class="validate" type="email" name="email" value="${sessionScope.email}" required>
                    <label for="icon_mail"><fmt:message key="account.email" bundle="${lang}"/></label>
                </div>
            </div>

            <div class="row">
                <div class="col s8 offset-s2">
                    <div class="demo">
                        <div class="payment-card">
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

                            <div class="row">
                                <div class="input-field col s8">
                                    <button class="btn waves-effect waves-light pink darken-4" type="submit" name="action"><fmt:message
                                            key="register.sign" bundle="${lang}"/>
                                        <i class="material-icons right">send</i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<br/>
<c:import url="parts/footer.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</body>
</html>
