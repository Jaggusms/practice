
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskSchedulerService {

    // 1. Execute every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void executeAtFixedRate() {

        System.out.println(
                "Fixed Rate Task: " + LocalDateTime.now()
        );
    }

    // 2. Execute 5 seconds after the previous execution finishes
    @Scheduled(fixedDelay = 5000)
    public void executeAtFixedDelay() {

        System.out.println(
                "Fixed Delay Task: " + LocalDateTime.now()
        );
    }

    // 3. Execute every 10 seconds using a cron expression
    @Scheduled(cron = "*/10 * * * * *")
    public void executeUsingCron() {

        System.out.println(
                "Cron Task: " + LocalDateTime.now()
        );
    }
}