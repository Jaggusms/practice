import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReModule {
    public static void main ( String[] args ) {

        String text = "my age is 26 and i have studies 12 + at 2017";

        ArrayList < String > numbers = new ArrayList <> ( );

        Matcher matcher = Pattern.compile ( "\\d+" ).matcher ( text );

        while ( matcher.find ( ) ) {
            numbers.add ( matcher.group ( ) );
        }

        System.out.println ( numbers );

        System.out.println (text.indexOf ( "26" ));
    }
}
