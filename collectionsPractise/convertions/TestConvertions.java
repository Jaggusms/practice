import java.util.Arrays;

public class TestConvertions {
    public static void main ( String[] args ) {
        int[] abc = { 1 , 2 , 3 , 4 };
        System.out.println ( Arrays.toString ( abc ) );


        int a = 123;
        Integer b = a;
        System.out.println ( "the type of the a is "+( b instanceof Integer )+" and we converting into string "+String.valueOf ( 123 ).getClass ( )+" "+123 );

        /*
| No. | Conversion | Method | Example | Return type |
|---:|---|---|---|---|
| 1 | `String` → `int` | `Integer.parseInt()` | `Integer.parseInt
("123")` | `int` |
| 2 | `String` → `Integer` | `Integer.valueOf()` | `Integer.valueOf
("123")` | `Integer` |
| 3 | `String` → `long` | `Long.parseLong()` | `Long.parseLong
("123456")` | `long` |
| 4 | `String` → `Long` | `Long.valueOf()` | `Long.valueOf
("123456")` | `Long` |
| 5 | `String` → `double` | `Double.parseDouble()` | `Double
.parseDouble("12.5")` | `double` |
| 6 | `String` → `Double` | `Double.valueOf()` | `Double.valueOf
("12.5")` | `Double` |
| 7 | `String` → `float` | `Float.parseFloat()` | `Float.parseFloat
("12.5")` | `float` |
| 8 | `String` → `Float` | `Float.valueOf()` | `Float.valueOf("12
.5")` | `Float` |
| 9 | `String` → `short` | `Short.parseShort()` | `Short.parseShort
("12")` | `short` |
| 10 | `String` → `byte` | `Byte.parseByte()` | `Byte.parseByte
("12")` | `byte` |
| 11 | `String` → `boolean` | `Boolean.parseBoolean()` | `Boolean
.parseBoolean("true")` | `boolean` |
| 12 | `String` → `Boolean` | `Boolean.valueOf()` | `Boolean
.valueOf("true")` | `Boolean` |
         */




        /*
| No. | Conversion | Method | Example | Return type |
|---:|---|---|---|---|
| 1 | `int` → `String` | `String.valueOf()` | `String.valueOf(123)`
 | `String` |
| 2 | `int` → `String` | `Integer.toString()` | `Integer.toString
(123)` | `String` |
| 3 | `long` → `String` | `String.valueOf()` | `String.valueOf
(123L)` | `String` |
| 4 | `double` → `String` | `String.valueOf()` | `String.valueOf(12
.5)` | `String` |
| 5 | `float` → `String` | `String.valueOf()` | `String.valueOf(12
.5f)` | `String` |
| 6 | `boolean` → `String` | `String.valueOf()` | `String.valueOf
(true)` | `String` |
| 7 | `char` → `String` | `String.valueOf()` | `String.valueOf('A')
` | `String` |
| 8 | Primitive → `String` | String concatenation | `123 + ""` |
`String` |
| 9 | Primitive → `String` | `String.format()` | `String.format
("%d", 123)` | `String` |
         */

/*
        | From | To | Example |
          |---|---|---|
          | `int` | `byte` | `byte x = (byte) 130;` |
| `int` | `short` | `short x = (short) 1000;` |
| `long` | `int` | `int x = (int) 100L;` |
| `double` | `int` | `int x = (int) 12.9;` |
| `float` | `int` | `int x = (int) 12.9f;` |
| `double` | `float` | `float x = (float) 12.5;` |
 */
        String name = "Java";

        char[] chars = name.toCharArray ( );

        System.out.println ( chars );
    }
}
