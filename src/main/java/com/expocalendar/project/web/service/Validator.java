package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.CreditCard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Validator {

    public static boolean validAccountParameters(Map<String, String> requestParameters) {
        return (!requestParameters.containsKey(null)) && (!requestParameters.containsValue(null));
    }

    public static boolean validLogin(String login, String password) {
        return (login != null) && (password != null);
    }


    public static void validateSelectionParameters(Map<String, String> requestParameters) {

        requestParameters.putIfAbsent("theme", "all");
        requestParameters.putIfAbsent("hallId", "");

        if (requestParameters.get("dateFrom") == null || requestParameters.get("dateFrom").equals("")) {
            requestParameters.put("dateFrom", getCurrentDate());
        }

        if (requestParameters.get("dateTo") == null || requestParameters.get("dateTo").equals("")) {
            requestParameters.put("dateTo", getCurrentDate());
        }
    }

    private static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static boolean validCard(Map<String, String> requestParameters, CreditCard creditCard, int withdraw) {
        return creditCard.getBalance() >= withdraw && requestParameters.get("cardHolder").equals(creditCard.getHolder()) &&
                requestParameters.get("cardNumber").equals(creditCard.getNumber()) &&
                requestParameters.get("cvv").equals(creditCard.getCVV()) &&
                requestParameters.get("month").equals(creditCard.getMonth()) &&
                requestParameters.get("year").equals(creditCard.getYear());
    }
}
