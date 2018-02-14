package su.vfe.foodmanager.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final LocalDateTime MIN_DATE_TIME = LocalDateTime.of(1, 1, 1, 1, 1, 1);
    public static final LocalDateTime MAX_DATE_TIME = LocalDateTime.of(3000, 1, 1, 1, 1, 1);

    private DateTimeUtil() {
    }
}
