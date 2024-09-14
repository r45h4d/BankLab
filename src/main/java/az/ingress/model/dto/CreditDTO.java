package az.ingress.model.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreditDTO {
    Integer term;
    BigDecimal interest;
    BigDecimal monthlyPayment;
    BigDecimal amount;
}
