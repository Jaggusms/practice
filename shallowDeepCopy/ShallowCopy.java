import java.util.Date;

class Employee implements Cloneable {

    String name;
    Date date;

    Employee(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class ShallowCopy {

    public static void main(String[] args) throws Exception {

        Employee emp1 = new Employee("Java", new Date());

        Employee emp2 = (Employee) emp1.clone();

        emp2.date.setTime(0);

        System.out.println(emp1.date);
        System.out.println(emp2.date);
    }
}