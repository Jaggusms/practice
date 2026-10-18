import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateTimePractice {

        private static final ZoneId IST =
                ZoneId.of("Asia/Kolkata");

        private static final ZoneId UTC =
                ZoneOffset.UTC;

        private static final ZoneId NEW_YORK =
                ZoneId.of("America/New_York");

    public static void main(String[] args) {

        // =========================================
        // 1. CURRENT DATE AND TIME
        // =========================================

        System.out.println("\n--- CURRENT DATE AND TIME ---");

        ZonedDateTime istNow = ZonedDateTime.now(IST);
        ZonedDateTime utcNow = ZonedDateTime.now(UTC);
        ZonedDateTime easternNow = ZonedDateTime.now(NEW_YORK);

        System.out.println("IST:      " + istNow);
        System.out.println("UTC:      " + utcNow);
        System.out.println("New York: " + easternNow);

        // Fixed EST = UTC-05:00, regardless of daylight saving
        ZonedDateTime fixedEST =
                Instant.now().atZone(ZoneOffset.ofHours(-5));

        System.out.println("Fixed EST: " + fixedEST);


        // =========================================
        // 2. EXTRACT INDIVIDUAL COMPONENTS
        // =========================================

        System.out.println("\n--- DATE/TIME COMPONENTS ---");

        System.out.println("Year:        " + istNow.getYear());
        System.out.println("Month:       " + istNow.getMonth());
        System.out.println("Month value: " + istNow.getMonthValue());
        System.out.println("Day:         " + istNow.getDayOfMonth());
        System.out.println("Day of week: " + istNow.getDayOfWeek());
        System.out.println("Day of year: " + istNow.getDayOfYear());

        System.out.println("Hour:        " + istNow.getHour());
        System.out.println("Minute:      " + istNow.getMinute());
        System.out.println("Second:      " + istNow.getSecond());
        System.out.println("Nano:        " + istNow.getNano());

        System.out.println("Offset:      " + istNow.getOffset());
        System.out.println("Zone:        " + istNow.getZone());

        LocalDate date = istNow.toLocalDate();
        LocalTime time = istNow.toLocalTime();

        System.out.println("Date only:   " + date);
        System.out.println("Time only:   " + time);


        // =========================================
        // 3. ADD AND SUBTRACT TIME
        // =========================================

        System.out.println("\n--- ADD AND SUBTRACT ---");

        LocalDateTime now = LocalDateTime.now();

        System.out.println("Original:       " + now);

        System.out.println("+10 seconds:    " + now.plusSeconds(10));
        System.out.println("-10 seconds:    " + now.minusSeconds(10));

        System.out.println("+10 minutes:    " + now.plusMinutes(10));
        System.out.println("-10 minutes:    " + now.minusMinutes(10));

        System.out.println("+10 hours:      " + now.plusHours(10));
        System.out.println("-10 hours:      " + now.minusHours(10));

        System.out.println("+10 days:       " + now.plusDays(10));
        System.out.println("-10 days:       " + now.minusDays(10));

        System.out.println("+10 months:     " + now.plusMonths(10));
        System.out.println("-10 months:     " + now.minusMonths(10));

        System.out.println("+10 years:      " + now.plusYears(10));
        System.out.println("-10 years:      " + now.minusYears(10));


        // =========================================
        // 4. STRING TO JAVA DATE/TIME
        // =========================================

        System.out.println("\n--- STRING TO JAVA DATE/TIME ---");

        LocalDate parsedDate =
                LocalDate.parse("2026-10-17");

        LocalTime parsedTime =
                LocalTime.parse("18:30:45");

        LocalDateTime parsedDateTime =
                LocalDateTime.parse("2026-10-17T18:30:45");

        System.out.println("Date:     " + parsedDate);
        System.out.println("Time:     " + parsedTime);
        System.out.println("DateTime: " + parsedDateTime);


        // Custom format: dd-MM-yyyy HH:mm:ss

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss",
                        Locale.ENGLISH
                );

        LocalDateTime customParsed =
                LocalDateTime.parse(
                        "17-10-2026 18:30:45",
                        formatter
                );

        System.out.println("Custom parsed: " + customParsed);


        // =========================================
        // 5. JAVA DATE/TIME TO STRING
        // =========================================

        System.out.println("\n--- JAVA DATE/TIME TO STRING ---");

        String dateString =
                date.format(
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")
                );

        String timeString =
                time.format(
                        DateTimeFormatter.ofPattern("HH:mm:ss")
                );

        String dateTimeString =
                now.format(formatter);

        System.out.println("Date string:     " + dateString);
        System.out.println("Time string:     " + timeString);
        System.out.println("DateTime string: " + dateTimeString);


        // =========================================
        // 6. ISO FORMAT
        // =========================================

        System.out.println("\n--- ISO FORMAT ---");

        String isoDate =
                date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        String isoTime =
                time.format(DateTimeFormatter.ISO_LOCAL_TIME);

        String isoDateTime =
                now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        System.out.println("ISO Date:     " + isoDate);
        System.out.println("ISO Time:     " + isoTime);
        System.out.println("ISO DateTime: " + isoDateTime);


        // ISO STRING TO JAVA OBJECT

        LocalDate dateFromISO =
                LocalDate.parse(isoDate);

        LocalTime timeFromISO =
                LocalTime.parse(isoTime);

        LocalDateTime dateTimeFromISO =
                LocalDateTime.parse(isoDateTime);

        System.out.println("Parsed ISO date:     " + dateFromISO);
        System.out.println("Parsed ISO time:     " + timeFromISO);
        System.out.println("Parsed ISO datetime: " + dateTimeFromISO);


        // =========================================
        // 7. ISO WITH TIME ZONE
        // =========================================

        System.out.println("\n--- ISO WITH TIME ZONE ---");

        String isoZoned =
                istNow.format(
                        DateTimeFormatter.ISO_ZONED_DATE_TIME
                );

        System.out.println("ISO Zoned: " + isoZoned);

        ZonedDateTime zonedFromISO =
                ZonedDateTime.parse(isoZoned);

        System.out.println("Parsed Zoned: " + zonedFromISO);


        // =========================================
        // 8. INSTANT / UTC TIMESTAMP
        // =========================================

        System.out.println("\n--- INSTANT / UTC ---");

        Instant instant = Instant.now();

        String instantISO = instant.toString();

        System.out.println("Instant:    " + instant);
        System.out.println("ISO String: " + instantISO);

        Instant instantFromISO =
                Instant.parse(instantISO);

        System.out.println("Parsed Instant: " + instantFromISO);


        // =========================================
        // 9. CONVERT THE SAME INSTANT TO TIME ZONES
        // =========================================

        System.out.println("\n--- SAME INSTANT IN DIFFERENT ZONES ---");

        Instant sameInstant = Instant.now();

        System.out.println(
                "IST: " + sameInstant.atZone(IST)
        );

        System.out.println(
                "UTC: " + sameInstant.atZone(UTC)
        );

        System.out.println(
                "New York: " + sameInstant.atZone(NEW_YORK)
        );


        // =========================================
        // 10. DURATION AND PERIOD
        // =========================================

        System.out.println("\n--- DURATION AND PERIOD ---");

        Duration tenHours = Duration.ofHours(10);

        System.out.println(
                "10 hours in minutes: " + tenHours.toMinutes()
        );

        Period tenDays = Period.ofDays(10);

        System.out.println("Period: " + tenDays);


        // =========================================
        // 11. DIFFERENCE BETWEEN TWO DATE/TIMES
        // =========================================

        System.out.println("\n--- DATE/TIME DIFFERENCE ---");

        LocalDateTime start =
                LocalDateTime.of(2026, 10, 17, 10, 0);

        LocalDateTime end =
                LocalDateTime.of(2026, 10, 17, 12, 30);

        long minutes =
                ChronoUnit.MINUTES.between(start, end);

        System.out.println("Difference in minutes: " + minutes);
    }
}