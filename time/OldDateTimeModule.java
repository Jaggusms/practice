import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class OldDateTimeModule {

    public static void main(String[] args) throws Exception {

        // 1. Date - Current date and time
        Date date = new Date();

        System.out.println("Date: " + date);

        // 2. Date - Get milliseconds
        long milliseconds = date.getTime();

        System.out.println("Milliseconds: " + milliseconds);

        // 3. Calendar - Current calendar
        Calendar calendar = Calendar.getInstance();

        System.out.println("Calendar Date: " + calendar.getTime());

        // Add days
        calendar.add(Calendar.DAY_OF_MONTH, 5);

        System.out.println("After 5 Days: " + calendar.getTime());

        // 4. GregorianCalendar
        GregorianCalendar gregorianCalendar =
                new GregorianCalendar();

        System.out.println("Year: " +
                gregorianCalendar.get(Calendar.YEAR));

        System.out.println("Month: " +
                (gregorianCalendar.get(Calendar.MONTH) + 1));

        // 5. TimeZone
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");

        System.out.println("Timezone: " + timeZone.getID());

        // 6. SimpleTimeZone
        SimpleTimeZone simpleTimeZone =
                new SimpleTimeZone(5 * 60 * 60 * 1000 + 30 * 60 * 1000,
                        "IST");

        System.out.println("Simple TimeZone: " +
                simpleTimeZone.getID());

        // 7. SimpleDateFormat - Date to String
        SimpleDateFormat sdf =
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String dateString = sdf.format(date);

        System.out.println("Date to String: " + dateString);

        // 8. SimpleDateFormat - String to Date
        String input = "22-11-2026 18:30:45";

        Date parsedDate = sdf.parse(input);

        System.out.println("String to Date: " + parsedDate);

        // 9. Date modification
        Date modifiedDate = new Date();

        modifiedDate.setTime(0);

        System.out.println("Modified Date: " + modifiedDate);
    }
}