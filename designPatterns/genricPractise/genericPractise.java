package genricPractise;

import java.util.ArrayList;

class  Box<T> {
    private  final ArrayList<T> values = new ArrayList <> (  );
    public void add(T value){
        values.add ( value );
    }
    public T get(Integer index){
        return  values.get ( index );
    }

}

public class genericPractise {
    public static void main ( String[] args ) {
        Box<Integer> ageBox = new Box <> ();
        ageBox.add ( 123 );
        ageBox.add ( 1 );
        System.out.println (ageBox.get ( 0 ) );

        Box<String> ageBox1 = new Box <> ();
        ageBox1.add ( "123" );
        ageBox1.add ( "1" );
        System.out.println (ageBox1.get ( 0 ) );

    }

}
