import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void initializeService() {
        employeeService = new EmployeeService();
    }

    @Test
    void shouldAddValidEmployee() {
        EmployeeDTO employee =
                new EmployeeDTO(101, "Jagadeesh", 50000.0);

        employeeService.addEmployee(employee);

        assertEquals(1, employeeService.getEmployeeCount());
        assertEquals(
                "Jagadeesh",
                employeeService.getAllEmployees().get(0).getName()
        );
    }

    @Test
    void shouldRejectDuplicateEmployeeId() {
        EmployeeDTO employee1 =
                new EmployeeDTO(101, "Jagadeesh", 50000.0);

        EmployeeDTO employee2 =
                new EmployeeDTO(101, "Ravi", 60000.0);

        employeeService.addEmployee(employee1);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee2)
        );

        assertEquals(1, employeeService.getEmployeeCount());
    }

    @Test
    void shouldRejectNullEmployeeName() {
        EmployeeDTO employee =
                new EmployeeDTO(102, null, 40000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );

        assertEquals(0, employeeService.getEmployeeCount());
    }

    @Test
    void shouldRejectEmptyEmployeeName() {
        EmployeeDTO employee =
                new EmployeeDTO(103, "", 40000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );
    }

    @Test
    void shouldRejectBlankEmployeeName() {
        EmployeeDTO employee =
                new EmployeeDTO(104, "   ", 40000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );
    }

    @Test
    void shouldAllowNullSalary() {
        EmployeeDTO employee =
                new EmployeeDTO(105, "Ravi", null);

        assertDoesNotThrow(
                () -> employeeService.addEmployee(employee)
        );

        assertNull(
                employeeService.getAllEmployees().get(0).getSalary()
        );
    }

    @Test
    void shouldAllowSameNameWithDifferentEmployeeIds() {
        EmployeeDTO employee1 =
                new EmployeeDTO(106, "Ravi", 40000.0);

        EmployeeDTO employee2 =
                new EmployeeDTO(107, "Ravi", 45000.0);

        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);

        assertEquals(2, employeeService.getEmployeeCount());
    }

    @Test
    void shouldRejectNullEmployeeObject() {
        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(null)
        );

        assertEquals(0, employeeService.getEmployeeCount());
    }

    @Test
    void shouldRejectNullEmployeeId() {
        EmployeeDTO employee =
                new EmployeeDTO(null, "Kiran", 35000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );

        assertEquals(0, employeeService.getEmployeeCount());
    }

    @Test
    void shouldAddMultipleValidEmployees() {
        EmployeeDTO employee1 =
                new EmployeeDTO(108, "Ravi", 40000.0);

        EmployeeDTO employee2 =
                new EmployeeDTO(109, "Kiran", null);

        EmployeeDTO employee3 =
                new EmployeeDTO(110, "Suresh", 55000.0);

        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);

        assertEquals(3, employeeService.getEmployeeCount());
    }

    @Test
    void shouldReturnUnmodifiableEmployeeList() {
        EmployeeDTO employee =
                new EmployeeDTO(111, "Suresh", 50000.0);

        employeeService.addEmployee(employee);

        assertThrows(
                UnsupportedOperationException.class,
                () -> employeeService.getAllEmployees().clear()
        );

        assertEquals(1, employeeService.getEmployeeCount());
    }
}