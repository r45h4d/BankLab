package az.ingress.scheduler;

import az.ingress.service.concrete.CreditServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditScheduler {
    private final CreditServiceHandler creditService;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24 * 2)
    public void updateCreditStatus(){
        creditService.updateCreditStatus();
    }
}
