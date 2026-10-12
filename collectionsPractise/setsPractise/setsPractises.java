import java.util.*;

public class setsPractises {
    public static void main ( String[] args ) {
        HashSet<Integer> intValues = new HashSet <> ( Set.of(1,2,3,
                4,5,6) );
        System.out.println (intValues.add(10) );
        System.out.println (intValues );
        System.out.println ("adding "+10+" status: is " +intValues.add(10)+" "+  intValues);
        System.out.println (intValues );
        System.out.println (intValues.addAll (Set.of ( 2,1,30 ) ));
        System.out.println (intValues );
        for (int i: intValues){
            System.out.println (i );
        }
        intValues.forEach ( i ->System.out.println (i+2 ) );
        System.out.println ( intValues.contains ( 30 ));
        intValues.stream().map(String::valueOf).forEach ( System.out::println );

        System.out.println (intValues.removeAll ( Set.of ( 1,2,3 ) ) );
        System.out.println (intValues.removeAll ( Set.of (1,4, 3,
                99 )) );
        System.out.println (intValues );

        Iterator iter= intValues.iterator ();
        while( iter.hasNext () ){
            System.out.println (iter.next () );
        }


        // remvoe duplicates

        ArrayList<Integer> values = new ArrayList <> ( List.of ( 1,
                2,3,4,1,2
                ) );
        HashSet<Integer> remvoedDuplicates =
                new HashSet <> ( values );
        System.out.println (remvoedDuplicates );
        System.out.println (new HashSet <> ( new ArrayList <> ( List.of ( 1,
                2,3,4,1,2
        ) ) ) );
        System.out.println (new HashSet <> ( new ArrayList <> ( List.of ( "hi",
                "hello","hi","Hello"        ) ) ) );

        //find DUplicates


        List<String> names = new ArrayList<>(
                List.of("Ravi", "Amit", "John", "David")
        );

//        Collections.sort(names, String::compareTo);
        Collections.sort(names);
        names.sort ((a,b)-> a.compareTo ( b )  );
//        names.sort ( Comparator.comparing (  ) .reversed ());
        System.out.println(names);
    }
}
