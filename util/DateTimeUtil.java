package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
    /**
     * convert from dd/MM/yyyy -> LocalDate
     * @param dateStr
     * @return
     */
    public static LocalDateTime convertStringToLocalDate(String dateStr) {
        return LocalDateTime.parse(dateStr, formatter).truncatedTo(ChronoUnit.SECONDS);
    }

    public static String convertLocalDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

}
