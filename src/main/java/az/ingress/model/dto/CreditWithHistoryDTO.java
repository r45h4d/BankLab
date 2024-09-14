package az.ingress.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class CreditWithHistoryDTO {
    CreditDTO credit;
    List<StatusHistoryDTO> statusHistories;
}
