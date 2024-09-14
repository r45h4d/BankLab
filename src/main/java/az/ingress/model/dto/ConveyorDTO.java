package az.ingress.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConveyorDTO {
    private int conveyorId;
    private String checkDate;
    private List<ProductDTO> products;
}
