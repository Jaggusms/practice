// public class Employee implements Serializable {

//     private int id;
//     private String name;
//     private String department;
//     private double salary;

//     public Employee(int id, String name, String department, double salary) {
//         this.id = id;
//         this.name = name;
//         this.department = department;
//         this.salary = salary;
//     }

//     public int getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getDepartment() {
//         return department;
//     }

//     public double getSalary() {
//         return salary;
//     }
// }



// List<Employee> employeeList = Arrays.asList(
//     new Employee(1, "Jagadeesh", "IT", 80000),
//     new Employee(2, "Ravi", "HR", 50000),
//     new Employee(3, "Krishna", "IT", 90000),
//     new Employee(4, "Suresh", "Finance", 60000)
// );

// JavaRDD<Employee> employeesRDD =
//         spark.sparkContext()
//                 .parallelize(employeeList, 2)
//                 .toJavaRDD();



// JavaRDD<Employee> result = employeesRDD.filter(
//         employee ->
//                 employee.getDepartment().equals("IT")
//                 && employee.getSalary() > 70000
// );

// result.collect().forEach(employee ->
//         System.out.println(
//                 employee.getName() + " " + employee.getSalary()
//         )
// );





// DataFrame

// id,name,department,salary
// 1,Jagadeesh,IT,80000
// 2,Ravi,HR,50000
// 3,Krishna,IT,90000
// 4,Suresh,Finance,60000

// Dataset<Row> employeesDF = spark.read()
//         .option("header", true)
//         .option("inferSchema", true)
//         .csv("employees.csv");


// Dataset<Row> result = employeesDF.filter(
//         col("department").equalTo("IT")
//         .and(col("salary").gt(70000))
// );

// result.select("name", "salary").show();







// Dataset<Employee> employeesDS =
//         spark.read()
//                 .option("header", true)
//                 .option("inferSchema", true)
//                 .csv("employees.csv")
//                 .as(Encoders.bean(Employee.class));


// Dataset<Employee> result = employeesDS.filter(
//         employee ->
//                 employee.getDepartment().equals("IT")
//                 && employee.getSalary() > 70000
// );


// result.show();