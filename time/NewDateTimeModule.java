import java.time.*;
import java.time.format.DateTimeFormatter;

public class NewDateTimeModule {

    public static void main(String[] args) {

        // 1. LocalDate - Date only
        LocalDate localDate = LocalDate.now();

        System.out.println("LocalDate: " + localDate);

        System.out.println("Year: " + localDate.getYear());

        System.out.println("Month: " + localDate.getMonth());

        System.out.println("Day: " + localDate.getDayOfMonth());

        System.out.println("After 5 Days: " +
                localDate.plusDays(5));


        // 2. LocalTime - Time only
        LocalTime localTime = LocalTime.now();

        System.out.println("\nLocalTime: " + localTime);

        System.out.println("Hour: " + localTime.getHour());

        System.out.println("Minute: " + localTime.getMinute());

        System.out.println("Second: " + localTime.getSecond());

        System.out.println("After 2 Hours: " +
                localTime.plusHours(2));


        // 3. LocalDateTime - Date + Time
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("\nLocalDateTime: " + localDateTime);

        System.out.println("After 5 Days: " +
                localDateTime.plusDays(5));


        // 4. ZonedDateTime - Date + Time + Timezone
        ZonedDateTime zonedDateTime =
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

        System.out.println("\nZonedDateTime: " + zonedDateTime);

        System.out.println("Timezone: " +
                zonedDateTime.getZone());


        // 5. OffsetDateTime - Date + Time + UTC Offset
        OffsetDateTime offsetDateTime =
                OffsetDateTime.now(ZoneOffset.of("+05:30"));

        System.out.println("\nOffsetDateTime: " + offsetDateTime);


        // 6. OffsetTime - Time + UTC Offset
        OffsetTime offsetTime =
                OffsetTime.now(ZoneOffset.of("+05:30"));

        System.out.println("\nOffsetTime: " + offsetTime);


        // 7. Instant - UTC Timestamp
        Instant instant = Instant.now();

        System.out.println("\nInstant: " + instant);

        System.out.println("Epoch Milliseconds: " +
                instant.toEpochMilli());


        // 8. Duration - Time difference
        LocalTime startTime = LocalTime.of(10, 0);

        LocalTime endTime = LocalTime.of(12, 30);

        Duration duration =
                Duration.between(startTime, endTime);

        System.out.println("\nDuration Hours: " +
                duration.toHours());

        System.out.println("Duration Minutes: " +
                duration.toMinutes());


        // 9. Period - Date difference
        LocalDate startDate = LocalDate.of(2020, 1, 1);

        LocalDate endDate = LocalDate.of(2026, 11, 22);

        Period period = Period.between(startDate, endDate);

        System.out.println("\nPeriod Years: " +
                period.getYears());

        System.out.println("Period Months: " +
                period.getMonths());

        System.out.println("Period Days: " +
                period.getDays());


        // 10. Year
        Year year = Year.now();

        System.out.println("\nYear: " + year);

        System.out.println("Is Leap Year: " +
                year.isLeap());


        // 11. YearMonth
        YearMonth yearMonth = YearMonth.now();

        System.out.println("\nYearMonth: " + yearMonth);

        System.out.println("Number of Days: " +
                yearMonth.lengthOfMonth());


        // 12. MonthDay
        MonthDay monthDay = MonthDay.now();

        System.out.println("\nMonthDay: " + monthDay);

        System.out.println("Month: " +
                monthDay.getMonth());

        System.out.println("Day: " +
                monthDay.getDayOfMonth());


        // 13. Month
        Month month = Month.NOVEMBER;

        System.out.println("\nMonth: " + month);

        System.out.println("Month Number: " +
                month.getValue());

        System.out.println("Month Name: " +
                month.name());


        // 14. DayOfWeek
        DayOfWeek dayOfWeek = DayOfWeek.SUNDAY;

        System.out.println("\nDayOfWeek: " + dayOfWeek);

        System.out.println("Day Number: " +
                dayOfWeek.getValue);

        System.out.println("Next Day: " +
                dayOfWeek.plus(1));


        // 15. DateTimeFormatter - Date to String
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = localDateTime.format(formatter);

        System.out.println("\nDate to String: " + formattedDate);


        // 16. String to DateTime
        String input = "22-11-2026 18:30:45";

        LocalDateTime parsedDateTime =
                LocalDateTime.parse(input, formatter);

        System.out.println("String to DateTime: " +
                parsedDateTime);
    }
}