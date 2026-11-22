import java.util.Date;

class Employee {

    String name;
    Date date;

    Employee(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    // Deep Copy Constructor
    Employee(Employee emp) {

        this.name = emp.name;

        this.date = new Date(emp.date.getTime());
    }
}

public class DeepCopy {

    public static void main(String[] args) {

        Employee emp1 = new Employee("Java", new Date());

        Employee emp2 = new Employee(emp1);

        emp2.date.setTime(0);

        System.out.println(emp1.date);
        System.out.println(emp2.date);
    }
}