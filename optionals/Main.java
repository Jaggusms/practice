import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // ==================== EMPLOYEE DTO ====================

    static class Employee {

        private Integer id;
        private String name;
        private String department;
        private Double salary;

        public Employee(Integer id, String name,
                        String department, Double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public Double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    // ==================== MAIN METHOD ====================

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                1, "Jagadeesh", "IT", 100000.0));

        employees.add(new Employee(
                2, "Rahul", "IT", 120000.0));

        employees.add(new Employee(
                3, "Priya", "HR", 80000.0));

        employees.add(new Employee(
                4, "Sneha", "HR", 90000.0));

        employees.add(new Employee(
                5, "Kiran", "Finance", 150000.0));

        employees.add(new Employee(
                6, "Arun", "Finance", 130000.0));

        employees.add(new Employee(
                7, "Ravi", "IT", 110000.0));

        employees.add(new Employee(
                8, "Anu", "Sales", 70000.0));

        employees.add(new Employee(
                9, "Vijay", "Sales", 95000.0));

        employees.add(new Employee(
                10, "Manoj", "IT", 140000.0));

        employees.add(new Employee(
                11, "Suresh", "IT", 105000.0));

        // Employee with null name
        employees.add(new Employee(
                12, null, "IT", 85000.0));

        // Employee with null department
        employees.add(new Employee(
                13, "Ramesh", null, 75000.0));

        // Employee with null salary
        employees.add(new Employee(
                14, "Pavan", "HR", null));

        // Employee with null ID
        employees.add(new Employee(
                null, "Divya", "Finance", 60000.0));

        // Null employee object
        employees.add(null);

        System.out.println(
                "========== METHOD 1: TRADITIONAL JAVA ==========");

        traditionalTotalSalary(employees);
        traditionalMaximumSalary(employees);
        traditionalDepartmentWiseMaximumSalary(employees);
        traditionalTop10Salaries(employees);

        System.out.println(
                "\n========== METHOD 2: JAVA STREAMS ==========");

        streamTotalSalary(employees);
        streamMaximumSalary(employees);
        streamDepartmentWiseMaximumSalary(employees);
        streamTop10Salaries(employees);
    }

    // ==========================================================
    // METHOD 1: TRADITIONAL JAVA + OPTIONAL
    // ==========================================================

    // 1. TOTAL SALARY

    static void traditionalTotalSalary(List<Employee> employees) {

        double total = 0.0;

        for (Employee employee : employees) {

            double salary = Optional.ofNullable(employee)
                    .map(Employee::getSalary)
                    .orElse(0.0);

            total += salary;
        }

        System.out.println("Total Salary: " + total);
    }

    // 2. MAXIMUM SALARY

    static void traditionalMaximumSalary(List<Employee> employees) {

        Double maxSalary = null;

        for (Employee employee : employees) {

            Double salary = Optional.ofNullable(employee)
                    .map(Employee::getSalary)
                    .orElse(null);

            if (salary != null) {

                if (maxSalary == null ||
                        Double.compare(salary, maxSalary) > 0) {

                    maxSalary = salary;
                }
            }
        }

        Optional<Double> result =
                Optional.ofNullable(maxSalary);

        System.out.println(
                "Maximum Salary: " +
                        result.map(String::valueOf)
                                .orElse("No salary available")
        );
    }

    // 3. DEPARTMENT-WISE MAXIMUM SALARY

    static void traditionalDepartmentWiseMaximumSalary(
            List<Employee> employees) {

        Map<String, Double> departmentMaxSalary =
                new HashMap<>();

        for (Employee employee : employees) {

            if (employee == null) {
                continue;
            }

            Double salary = Optional.ofNullable(employee)
                    .map(Employee::getSalary)
                    .orElse(null);

            if (salary == null) {
                continue;
            }

            String department = Optional.ofNullable(employee)
                    .map(Employee::getDepartment)
                    .filter(d -> !d.isBlank())
                    .orElse("Unknown");

            Double existingMax =
                    departmentMaxSalary.get(department);

            if (existingMax == null ||
                    Double.compare(salary, existingMax) > 0) {

                departmentMaxSalary.put(department, salary);
            }
        }

        System.out.println(
                "Department-wise Maximum Salary: " +
                        departmentMaxSalary
        );
    }

    // 4. TOP 10 HIGHEST-PAID EMPLOYEES

    static void traditionalTop10Salaries(
            List<Employee> employees) {

        List<Employee> validEmployees = new ArrayList<>();

        for (Employee employee : employees) {

            if (employee == null) {
                continue;
            }

            Optional<Double> salary =
                    Optional.ofNullable(employee)
                            .map(Employee::getSalary);

            if (salary.isPresent()) {
                validEmployees.add(employee);
            }
        }

        // Sort salaries in descending order.
        validEmployees.sort(
                (e1, e2) ->
                        Double.compare(e2.getSalary(), e1.getSalary())
        );

        System.out.println("Top 10 Employees:");

        int limit = Math.min(10, validEmployees.size());

        for (int i = 0; i < limit; i++) {

            Employee employee = validEmployees.get(i);

            String name = Optional.ofNullable(employee.getName())
                    .filter(n -> !n.isBlank())
                    .orElse("Unknown");

            String department =
                    Optional.ofNullable(employee.getDepartment())
                            .filter(d -> !d.isBlank())
                            .orElse("Unknown");

            System.out.println(
                    "Name: " + name +
                            ", Department: " + department +
                            ", Salary: " + employee.getSalary()
            );
        }
    }

    // ==========================================================
    // METHOD 2: JAVA STREAMS + OPTIONAL
    // ==========================================================

    // 1. TOTAL SALARY

    static void streamTotalSalary(List<Employee> employees) {

        double total = employees.stream()

                // Remove null employee objects.
                .filter(Objects::nonNull)

                // Get salaries, replacing null with zero.
                .map(employee ->
                        Optional.ofNullable(employee.getSalary())
                                .orElse(0.0)
                )

                // Add all salaries.
                .mapToDouble(Double::doubleValue)
                .sum();

        System.out.println("Total Salary: " + total);
    }

    // 2. MAXIMUM SALARY

    static void streamMaximumSalary(List<Employee> employees) {

        Optional<Double> maxSalary = employees.stream()

                // Ignore null employee objects.
                .filter(Objects::nonNull)

                // Get salaries.
                .map(Employee::getSalary)

                // Ignore null salaries.
                .filter(Objects::nonNull)

                // Find maximum salary.
                .max(Double::compareTo);

        System.out.println(
                "Maximum Salary: " +
                        maxSalary.map(String::valueOf)
                                .orElse("No salary available")
        );
    }

    // 3. DEPARTMENT-WISE MAXIMUM SALARY

    static void streamDepartmentWiseMaximumSalary(
            List<Employee> employees) {

        Map<String, Double> departmentMaxSalary =
                employees.stream()

                        // Remove null employee objects.
                        .filter(Objects::nonNull)

                        // Ignore null salaries.
                        .filter(employee ->
                                employee.getSalary() != null)

                        // Group by department and find max salary.
                        .collect(Collectors.toMap(

                                employee ->
                                        Optional.ofNullable(
                                                        employee.getDepartment())
                                                .filter(d -> !d.isBlank())
                                                .orElse("Unknown"),

                                Employee::getSalary,

                                Double::max
                        ));

        System.out.println(
                "Department-wise Maximum Salary: " +
                        departmentMaxSalary
        );
    }

    // 4. TOP 10 HIGHEST-PAID EMPLOYEES

    static void streamTop10Salaries(List<Employee> employees) {

        List<Employee> top10 = employees.stream()

                // Ignore null employee objects.
                .filter(Objects::nonNull)

                // Ignore null salaries.
                .filter(employee ->
                        employee.getSalary() != null)

                // Highest salary first.
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()
                )

                // Select the first 10.
                .limit(10)

                // Collect the results into a List.
                .collect(Collectors.toList());

        System.out.println("Top 10 Employees:");

        top10.forEach(employee -> {

            String name = Optional.ofNullable(employee.getName())
                    .filter(n -> !n.isBlank())
                    .orElse("Unknown");

            String department =
                    Optional.ofNullable(employee.getDepartment())
                            .filter(d -> !d.isBlank())
                            .orElse("Unknown");

            System.out.println(
                    "Name: " + name +
                            ", Department: " + department +
                            ", Salary: " + employee.getSalary()
            );
        });
    }
}