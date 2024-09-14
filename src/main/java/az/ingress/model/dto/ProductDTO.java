package az.ingress.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private int amount;
    private int monthlyPayment;
    private int interest;
    private int term;
    private int productId;
}
