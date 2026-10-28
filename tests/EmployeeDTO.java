public class EmployeeDTO {

    private Integer employeeId;
    private String name;
    private Double salary;

    public EmployeeDTO(Integer employeeId, String name, Double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}