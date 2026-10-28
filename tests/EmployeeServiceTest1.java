import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    // Test 1: Successfully add an employee
    @Test
    void shouldAddEmployeeSuccessfully() {

        EmployeeDTO employee =
                new EmployeeDTO(101, "Ravi", 50000.0);

        when(employeeRepository.existsByEmployeeId(101))
                .thenReturn(false);

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        EmployeeDTO result = employeeService.addEmployee(employee);

        assertNotNull(result);
        assertEquals(101, result.getEmployeeId());
        assertEquals("Ravi", result.getName());
        assertEquals(50000.0, result.getSalary());

        verify(employeeRepository).existsByEmployeeId(101);
        verify(employeeRepository).save(employee);
    }

    // Test 2: Reject a duplicate employee ID
    @Test
    void shouldRejectDuplicateEmployeeId() {

        EmployeeDTO employee =
                new EmployeeDTO(101, "Ravi", 50000.0);

        when(employeeRepository.existsByEmployeeId(101))
                .thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );

        verify(employeeRepository).existsByEmployeeId(101);

        // A duplicate must never be saved.
        verify(employeeRepository, never()).save(any(EmployeeDTO.class));
    }

    // Test 3: Reject a null employee name
    @Test
    void shouldRejectNullEmployeeName() {

        EmployeeDTO employee =
                new EmployeeDTO(102, null, 40000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );

        // Validation fails before the repository is called.
        verifyNoInteractions(employeeRepository);
    }

    // Test 4: Reject a blank employee name
    @Test
    void shouldRejectBlankEmployeeName() {

        EmployeeDTO employee =
                new EmployeeDTO(103, "   ", 40000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );

        verifyNoInteractions(employeeRepository);
    }

    // Test 5: Allow a null salary
    @Test
    void shouldAllowNullSalary() {

        EmployeeDTO employee =
                new EmployeeDTO(104, "Priya", null);

        when(employeeRepository.existsByEmployeeId(104))
                .thenReturn(false);

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        EmployeeDTO result = employeeService.addEmployee(employee);

        assertNotNull(result);
        assertNull(result.getSalary());

        verify(employeeRepository).save(employee);
    }

    // Test 6: Reject a negative salary
    @Test
    void shouldRejectNegativeSalary() {

        EmployeeDTO employee =
                new EmployeeDTO(105, "Kiran", -1000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );

        verifyNoInteractions(employeeRepository);
    }

    // Test 7: Reject a null employee ID
    @Test
    void shouldRejectNullEmployeeId() {

        EmployeeDTO employee =
                new EmployeeDTO(null, "Suresh", 35000.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(employee)
        );

        verifyNoInteractions(employeeRepository);
    }

    // Test 8: Reject a null employee object
    @Test
    void shouldRejectNullEmployee() {

        assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.addEmployee(null)
        );

        verifyNoInteractions(employeeRepository);
    }

    // Test 9: Retrieve all employees
    @Test
    void shouldReturnAllEmployees() {

        List<EmployeeDTO> employees = List.of(
                new EmployeeDTO(101, "Ravi", 50000.0),
                new EmployeeDTO(102, "Priya", null),
                new EmployeeDTO(103, "Kiran", 60000.0)
        );

        when(employeeRepository.findAll())
                .thenReturn(employees);

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertEquals(3, result.size());
        assertEquals("Ravi", result.get(0).getName());
        assertNull(result.get(1).getSalary());

        verify(employeeRepository).findAll();
    }

    // Test 10: Handle an empty employee list
    @Test
    void shouldReturnEmptyListWhenNoEmployeesExist() {

        when(employeeRepository.findAll())
                .thenReturn(List.of());

        List<EmployeeDTO> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(employeeRepository).findAll();
    }

    // Test 11: Propagate a repository exception
    @Test
    void shouldPropagateExceptionWhenRepositorySaveFails() {

        EmployeeDTO employee =
                new EmployeeDTO(106, "Ravi", 50000.0);

        when(employeeRepository.existsByEmployeeId(106))
                .thenReturn(false);

        when(employeeRepository.save(employee))
                .thenThrow(new RuntimeException("Database unavailable"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> employeeService.addEmployee(employee)
        );

        assertEquals("Database unavailable", exception.getMessage());

        verify(employeeRepository).save(employee);
    }
}