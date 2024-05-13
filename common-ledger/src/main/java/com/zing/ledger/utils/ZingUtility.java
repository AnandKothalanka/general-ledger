package com.zing.ledger.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ZingUtility {

    public static Calendar stringToCalendar(String calendarStr) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(sdf.parse(calendarStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static int CurrencyStringToInt(String currencyStr) {
        currencyStr = currencyStr.replaceAll("[^0-9]", "");
        return Integer.valueOf(currencyStr);
    }
}
