package az.ingress.model.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusHistoryDTO {
    String status;
    LocalDate createdAt;
    LocalDate updatedAt;

    Long creditId;
}
