package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    /**
     * convert from dd/MM/yyyy -> LocalDate
     * @param dateStr
     * @return
     */
    public static LocalDateTime convertStringToLocalDate(String dateStr) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static String convertLocalDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

}
