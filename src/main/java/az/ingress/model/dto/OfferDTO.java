package az.ingress.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class OfferDTO {
    Integer term;
    BigDecimal interest;
    BigDecimal monthlyPayment;
    BigDecimal amount;
    Boolean accepted;
    LocalDate createdAt;
    LocalDate updatedAt;
}
