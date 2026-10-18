import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Instant now = Instant.now();

        System.out.println(now);


        String time = "2026-10-17T13:53:01";

        LocalDateTime dateTime = LocalDateTime.parse(time);

        ZonedDateTime ist =
                dateTime.atZone(ZoneId.of("Asia/Kolkata"));

        ZonedDateTime utc =
                dateTime.atZone(ZoneOffset.UTC);

        ZonedDateTime newYork =
                dateTime.atZone(ZoneId.of("America/New_York"));

        System.out.println("IST: " + ist);
        System.out.println("UTC: " + utc);
        System.out.println("New York: " + newYork);

// | String | Java formatter pattern | Java type |
// |---|---|---|
// | `2026-10-17T13:53:01` | `yyyy-MM-dd'T'HH:mm:ss` | `LocalDateTime` |
// | `17-10-2026 13:53:01` | `dd-MM-yyyy HH:mm:ss` | `LocalDateTime` |
// | `17/10/2026 01:53 PM` | `dd/MM/yyyy hh:mm a` | `LocalDateTime` |
// | `2026-10-17` | `yyyy-MM-dd` | `LocalDate` |
// | `2026-10-17T13:53:01.123` | `yyyy-MM-dd'T'HH:mm:ss.SSS` | `LocalDateTime` |
// | `2026-10-17T13:53:01.123456` | `yyyy-MM-dd'T'HH:mm:ss.SSSSSS` | `LocalDateTime` |
// | `2026-10-17T13:53:01.123456789` | `yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS` | `LocalDateTime` |
// | `2026-10-17T13:53:01Z` | `yyyy-MM-dd'T'HH:mm:ssX` | `OffsetDateTime` |
// | `2026-10-17T13:53:01+05:30` | `yyyy-MM-dd'T'HH:mm:ssXXX` | `OffsetDateTime` |
// | `2026-10-17T13:53:01.123+05:30` | `yyyy-MM-dd'T'HH:mm:ss.SSSXXX` | `OffsetDateTime` |
// | `2026-10-17T13:53:01.123456789Z` | `yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX` | `OffsetDateTime` |
// | `2026-10-17T13:53:01+0530` | `yyyy-MM-dd'T'HH:mm:ssXX` | `OffsetDateTime` |
// | `2026-10-17T13:53:01-04:00` | `yyyy-MM-dd'T'HH:mm:ssXXX` | `OffsetDateTime` |
// | `2026-10-17T13:53:01.123-04:00` | `yyyy-MM-dd'T'HH:mm:ss.SSSXXX` | `OffsetDateTime` |
// | `2026-10-17T13:53:01+05:30[Asia/Kolkata]` | `yyyy-MM-dd'T'HH:mm:ssXXX'['VV']'` | `ZonedDateTime` |


// | Pattern | Meaning | Example |
// |---|---|---|
// | `X` | Offset, using `Z` for UTC | `Z`, `+05` |
// | `XX` | Offset without a colon | `Z`, `+0530` |
// | `XXX` | Offset with a colon | `Z`, `+05:30`, `-04:00` |
// | `VV` | Time-zone ID | `Asia/Kolkata`, `America/New_York` |
// | `z` | Time-zone name | `UTC`, `PST` (display depends on locale and zone) |
        String time1 = "17-10-2026 13:53:01";

        DateTimeFormatter formatter1 =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        LocalDateTime dateTime1 =
                LocalDateTime.parse(time1, formatter1);

        ZonedDateTime ist1 =
                dateTime1.atZone(ZoneId.of("Asia/Kolkata"));

        System.out.println(ist1);




        // String input = "2026-10-17T13:53:01.1234567";

        // DateTimeFormatter formatter =
        //         DateTimeFormatter.ofPattern(
        //                 "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");

        // LocalDateTime dateTime =
        //         LocalDateTime.parse(input, formatter);

        // System.out.println(dateTime);
        // System.out.println("Nanoseconds: " + dateTime.getNano());
    }
}