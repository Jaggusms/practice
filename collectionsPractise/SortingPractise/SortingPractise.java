import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
class Employee implements  Comparable<Employee> {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - " + age + " - " + salary;
    }

    @Override // this means it is in natural instion order..
    public int compareTo ( Employee employee ) {
        return 0;
    }

    public int ageComparator(Employee employee) {
        return Integer.compare(this.age, employee.age);
    }
}
public class SortingPractise {
    public static void main ( String[] args ) {
        List<Integer> list = new ArrayList <> ( List.of ( 1,4,2,1) );
        Collections.sort(list);
        System.out.println (list );
        List<Integer> list1 = new ArrayList <> ( List.of ( 1,4,2,1) );
        list1.sort ( (x,y) -> x-y );
        System.out.println (list1 );


        List<String> listS = new ArrayList <> ( List.of ( "a",
                "c", "b") );
        Collections.sort(listS);
        System.out.println (listS );
        List<String> listS1 = new ArrayList <> ( List.of ( "a",
                "c", "b") );
        listS1.sort ( (x,y) -> x.compareTo ( y ) );  // can't
        // applied x-y here
        System.out.println (listS1 );

        // reverse order
        list.sort ( Comparator.reverseOrder () );
        System.out.println (list);
        list.sort ( (x,y) ->x-y );
        System.out.println (list);


        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Ravi", 30, 60000));
        employees.add(new Employee("Jagadeesh", 25, 50000));
        employees.add(new Employee("Amit", 28, 70000));
        employees.add(new Employee("John", 35, 80000));
        Comparator<Employee>  nameCOmparator =
                (x,y)-> x.name.compareTo ( y.name );
        employees.sort ( nameCOmparator );
        System.out.println ( employees);

        Comparator<Employee> ageCOmparator1 =
                (e1,e2)-> Integer.compare (e1.age,e2.age  );
        Comparator<Employee> ageCOmparator2 =
                (e1,e2)-> e1.age-e2.age;
        Comparator<Employee> ageCOmparator3 =
                (e1,e2)-> e1.ageComparator ( e2 );
        Comparator<Employee> ageCOmparator4 =
                Comparator.comparingInt (  (Employee e) -> e.age );
        employees.sort ( ageCOmparator1 );
        employees.sort ( ageCOmparator2 );
        employees.sort ( ageCOmparator3 );
        employees.sort ( ageCOmparator4 );

        employees.sort ( (e1,e2)-> e1.ageComparator ( e2 ));
        System.out.println (employees );
        Comparator<Employee> multipleCompare1 =
                ageCOmparator1.thenComparing ( ageCOmparator2 ).thenComparing ( ageCOmparator3 );
        Comparator<Employee> multipleCompare2 =
                Comparator.comparing((Employee e) -> e.age)
                        .thenComparing(e -> e.name);


    }
}
