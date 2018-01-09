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


<div class="demo">
    <form class="payment-card">
        <div class="bank-card">
            <div class="bank-card__side bank-card__side_front">
                <div class="bank-card__inner">
                    <label class="bank-card__label bank-card__label_holder">
                        <span class="bank-card__hint">Holder of card</span>
                        <input type="text" class="bank-card__field" placeholder="Holder of card" pattern="[A-Za-z, ]{2,}" name="holder-card" required>
                    </label>
                </div>
                <div class="bank-card__inner">
                    <label class="bank-card__label bank-card__label_number">
                        <span class="bank-card__hint">Number of card</span>
                        <input type="text" class="bank-card__field" placeholder="Number of card" pattern="[0-9]{16}" name="number-card" required>
                    </label>
                </div>
                <div class="bank-card__inner">
                    <span class="bank-card__caption">valid thru</span>
                </div>
                <div class="bank-card__inner bank-card__footer">
                    <label class="bank-card__label bank-card__month">
                        <span class="bank-card__hint">Month</span>
                        <input type="text" class="bank-card__field" placeholder="MM" maxlength="2" pattern="[0-9]{2}" name="mm-card" required>
                    </label>
                    <span class="bank-card__separator">/</span>
                    <label class="bank-card__label bank-card__year">
                        <span class="bank-card__hint">Year</span>
                        <input type="text" class="bank-card__field" placeholder="YY" maxlength="2" pattern="[0-9]{2}" name="year-card" required>
                    </label>
                </div>
            </div>
            <div class="bank-card__side bank-card__side_back">
                <div class="bank-card__inner">
                    <label class="bank-card__label bank-card__cvc">
                        <span class="bank-card__hint">CVC</span>
                        <input type="text" class="bank-card__field" placeholder="CVC" maxlength="3" pattern="[0-9]{3}" name="cvc-card" required>
                    </label>
                </div>
            </div>
        </div>
        <div class="payment-card__footer">
            <button class="payment-card__button">Send</button>
        </div>
    </form>
</div>