package az.ingress;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        run(Application.class, args);
    }
}