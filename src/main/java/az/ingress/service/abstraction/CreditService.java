package az.ingress.service.abstraction;

import az.ingress.model.dto.ConveyorDTO;
import az.ingress.model.dto.CreditDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreditService {
    List<ConveyorDTO> initializeCredit(CreditDTO credit, Long customerId);
}
