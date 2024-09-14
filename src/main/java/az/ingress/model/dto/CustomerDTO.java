package az.ingress.model.dto;

import lombok.*;
@Getter
@Setter
@Builder
public class CustomerDTO {
    String pin;

    String fullName;

    String phoneNumber;
}
