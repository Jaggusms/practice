import com.example.batch.entity.Employee;
import com.example.batch.entity.ProcessedEmployee;
import com.example.batch.repository.ProcessedEmployeeRepository;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;
// spring.batch.jdbc.initialize-schema=always
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    // 1. Reader: Read employees from the database
    @Bean
    public RepositoryItemReader<Employee> reader(
            com.example.batch.repository.EmployeeRepository repository) {

        RepositoryItemReader<Employee> reader =
                new RepositoryItemReader<>();

        reader.setRepository(repository);
        reader.setMethodName("findAll");
        reader.setPageSize(10);
        reader.setSort(Map.of("id", Sort.Direction.ASC));

        return reader;
    }

    // 2. Processor: Increase salary by 10%
    @Bean
    public ItemProcessor<Employee, ProcessedEmployee> processor() {

        return employee -> {

            double updatedSalary = employee.getSalary() * 1.10;

            return new ProcessedEmployee(
                    employee.getName(),
                    updatedSalary
            );
        };
    }

    // 3. Writer: Save processed employees using JPA
    @Bean
    public RepositoryItemWriter<ProcessedEmployee> writer(
            ProcessedEmployeeRepository repository) {

        RepositoryItemWriter<ProcessedEmployee> writer =
                new RepositoryItemWriter<>();

        writer.setRepository(repository);
        writer.setMethodName("save");

        return writer;
    }

    // 4. Step: Connect Reader, Processor and Writer
    @Bean
    public Step employeeStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            RepositoryItemReader<Employee> reader,
            ItemProcessor<Employee, ProcessedEmployee> processor,
            RepositoryItemWriter<ProcessedEmployee> writer) {

        return new StepBuilder("employeeStep", jobRepository)
                .<Employee, ProcessedEmployee>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // 5. Job: Execute the Step
    @Bean
    public Job employeeJob(
            JobRepository jobRepository,
            Step employeeStep) {

        return new JobBuilder("employeeJob", jobRepository)
                .start(employeeStep)
                .build();
    }
}