You are correct that Cloneable is a Marker Interface, which means it does not contain any methods to implement.

So, why do we override clone()?

Let's understand the internal mechanism.
1. Where does clone() actually come from?
The clone() method is defined in the Object class, not in the Cloneable interface.
public class Object {

    protected native Object clone() throws CloneNotSupportedException;

}

Notice:
- clone() belongs to the Object class.
- It is protected.
- It is a native method.
2. What is the purpose of Cloneable?
Cloneable is a marker interface.
public interface Cloneable {
}

It tells the JVM that this class permits cloning through Object.clone().

If your class does not implement Cloneable and you call super.clone(), Java throws:
CloneNotSupportedException

3. Then why do we override clone()?
There are two main reasons.

Reason 1: To increase visibility

The clone() method in Object is protected.

If we want other classes to access it, we override it as public.


import java.util.*;

class Employee implements Cloneable {

    String name;
    Date date;
    ArrayList<String> skills;
    HashMap<Integer, String> projects;
    HashSet<String> locations;

    Employee(String name, Date date,
             ArrayList<String> skills,
             HashMap<Integer, String> projects,
             HashSet<String> locations) {

        this.name = name;
        this.date = date;
        this.skills = skills;
        this.projects = projects;
        this.locations = locations;
    }

    // SHALLOW COPY
    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

    // DEEP COPY
    public Employee deepCopy() {

        Employee emp = new Employee(
                this.name,
                new Date(this.date.getTime()),
                new ArrayList<>(this.skills),
                new HashMap<>(this.projects),
                new HashSet<>(this.locations)
        );

        return emp;
    }
}




import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        ArrayList<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring");

        HashMap<Integer, String> projects = new HashMap<>();
        projects.put(1, "Banking");
        projects.put(2, "Insurance");

        HashSet<String> locations = new HashSet<>();
        locations.add("Bangalore");
        locations.add("Hyderabad");

        Employee emp1 = new Employee(
                "John",
                new Date(),
                skills,
                projects,
                locations
        );

        // SHALLOW COPY
        Employee emp2 = (Employee) emp1.clone();

        emp2.skills.add("Microservices");

        System.out.println("Shallow Copy:");
        System.out.println(emp1.skills);
        System.out.println(emp2.skills);

        // DEEP COPY
        Employee emp3 = emp1.deepCopy();

        emp3.skills.add("Docker");

        System.out.println("\nDeep Copy:");
        System.out.println(emp1.skills);
        System.out.println(emp3.skills);
    }
}