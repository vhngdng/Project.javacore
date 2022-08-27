package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * convert from dd/MM/yyyy -> LocalDate
     * @param dateStr
     * @return
     */
    public static LocalDate convertStringToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static String convertLocalDateToString(LocalDate date) {
        return date.format(formatter);
    }

}
