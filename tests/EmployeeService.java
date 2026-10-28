
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeService {

    private final List<EmployeeDTO> employees = new ArrayList<>();

    public void addEmployee(EmployeeDTO employee) {

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

        boolean duplicateExists = employees.stream()
                .anyMatch(existing ->
                        Objects.equals(
                                existing.getEmployeeId(),
                                employee.getEmployeeId()
                        )
                );

        if (duplicateExists) {
            throw new IllegalArgumentException("Duplicate employee ID");
        }

        // Salary is optional.
        employees.add(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return List.copyOf(employees);
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}