import java.util.List;

public interface EmployeeRepository {

    boolean existsByEmployeeId(Integer employeeId);

    EmployeeDTO save(EmployeeDTO employee);

    List<EmployeeDTO> findAll();
}