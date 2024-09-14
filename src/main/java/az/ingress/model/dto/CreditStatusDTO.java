package az.ingress.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditStatusDTO {
    Long productId;
    String status;
}
