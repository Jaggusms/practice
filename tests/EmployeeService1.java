import java.util.List;

public class EmployeeService1 {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (employee.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }

        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Employee name cannot be null or blank"
            );
        }

        if (employee.getSalary() != null && employee.getSalary() < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        if (employeeRepository.existsByEmployeeId(employee.getEmployeeId())) {
            throw new IllegalArgumentException("Duplicate employee ID");
        }

        return employeeRepository.save(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll();
    }
}