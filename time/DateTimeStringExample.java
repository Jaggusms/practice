import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeStringExample {

    public static void main(String[] args) {

        // 1. Get current date and time in IST
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        // 2. Define different patterns
        DateTimeFormatter f1 =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter f2 =
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");

        DateTimeFormatter f3 =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter f4 =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

        DateTimeFormatter f5 =
                DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss");

        // 3. Convert date/time into String
        String s1 = istTime.format(f1);
        String s2 = istTime.format(f2);
        String s3 = istTime.format(f3);
        String s4 = istTime.format(f4);
        String s5 = istTime.format(f5);

        // 4. Print the results
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
    }
}