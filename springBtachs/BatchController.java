import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job employeeJob;

    public BatchController(
            JobLauncher jobLauncher,
            Job employeeJob) {

        this.jobLauncher = jobLauncher;
        this.employeeJob = employeeJob;
    }

    @PostMapping("/batch/run")
    public String runBatch() throws Exception {

        JobParameters parameters =
                new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis())
                        .toJobParameters();

        JobExecution execution =
                jobLauncher.run(employeeJob, parameters);

        return "Batch job started. Status: "
                + execution.getStatus();
    }
}