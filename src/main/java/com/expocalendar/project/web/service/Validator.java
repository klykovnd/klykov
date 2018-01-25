package com.expocalendar.project.web.service;

import com.expocalendar.project.entities.CreditCard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Validator {

    public static void validateSelectionParameters(Map<String, String> requestParameters) {
        requestParameters.putIfAbsent("theme", "all");
        requestParameters.putIfAbsent("hallId", "");

        if (requestParameters.get("dateFrom") == null || requestParameters.get("dateFrom").equals("")) {
            requestParameters.put("dateFrom", getCurrentDate());
        }

        if (requestParameters.get("dateTo") == null || requestParameters.get("dateTo").equals("")) {
            requestParameters.put("dateTo", getCurrentDate());
        }

        if (invalidDateRange(requestParameters)) {
            swapDate(requestParameters);
        }
    }

    private static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    private static boolean invalidDateRange(Map<String, String> requestParameters) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = format.parse(requestParameters.get("dateFrom"));
            date2 = format.parse(requestParameters.get("dateTo"));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1.after(date2);

    }

    private static void swapDate(Map<String, String> requestParameters) {
        String dateFrom = requestParameters.get("dateFrom");
        String dateTo = requestParameters.get("dateTo");

        requestParameters.put("dateFrom", dateTo);
        requestParameters.put("dateTo", dateFrom);
    }

    public static void validateTime(Map<String, String> requestParameters) {
        String time = requestParameters.get("beginTime");
        if (time.length() == 5) {
            time += ":00";
        }
        requestParameters.put("beginTime", time);
    }

    public static boolean validCard(Map<String, String> requestParameters, CreditCard creditCard, double withdraw) {
        return creditCard.getBalance() >= withdraw &&
                requestParameters.get("cardHolder").equals(creditCard.getHolder()) &&
                requestParameters.get("cardNumber").equals(creditCard.getNumber()) &&
                Integer.valueOf(requestParameters.get("cvv")) == (creditCard.getCVV()) &&
                Integer.valueOf(requestParameters.get("month")) == (creditCard.getMonth()) &&
                Integer.valueOf(requestParameters.get("year")) == (creditCard.getYear());
    }
}
