package by.academy.util;

import java.time.Month;

public final class MonthUtil {
    public static String getRussianMonthRepresentation(Month month) {
        switch (month) {
            case JANUARY:
                return "января";
            case FEBRUARY:
                return "февраля";
            case MARCH:
                return "марта";
            case APRIL:
                return "апреля";
            case MAY:
                return "мая";
            case JUNE:
                return "июня";
            case JULY:
                return "июля";
            case AUGUST:
                return "августа";
            case SEPTEMBER:
                return "сентября";
            case OCTOBER:
                return "октября";
            case NOVEMBER:
                return "ноября";
            case DECEMBER:
                return "декабря";
            default:
                return null;
        }
    }
}
