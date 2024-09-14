package az.ingress.controller;

import az.ingress.model.dto.ConveyorDTO;
import az.ingress.model.dto.CreditDTO;
import az.ingress.model.dto.CreditWithHistoryDTO;
import az.ingress.model.dto.OfferDTO;
import az.ingress.service.concrete.CreditServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static az.ingress.model.enums.CreditStatus.*;
import static az.ingress.model.enums.CreditStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class CreditController {
    private final CreditServiceHandler creditService;

    @PostMapping("/initializeCredit/customers/{customerId}")
    @ResponseStatus(CREATED)
    public List<ConveyorDTO> initializeCredit(@RequestBody CreditDTO credit, @PathVariable Long customerId){
        return creditService.initializeCredit(credit, customerId);
    }

    @GetMapping("/credits/{id}")
    @ResponseStatus(OK)
    public CreditWithHistoryDTO getCreditWithStatusHistoriesById(@PathVariable Long id){
        return creditService.getCreditWithStatusHistoriesById(id);
    }

    @GetMapping("/offers")
    @ResponseStatus(OK)
    public List<OfferDTO> getOffers(){
        return creditService.getOffer();
    }

    @PatchMapping("/accept/credits/{id}")
    @ResponseStatus(NO_CONTENT)
    public void acceptCredit(@PathVariable Long id){
        creditService.acceptCredit(id, ACCEPTED);
    }

    @PatchMapping("/reject/credits/{id}")
    @ResponseStatus(NO_CONTENT)
    public void rejectCredit(@PathVariable Long id){
        creditService.rejectCredit(id, REJECTED);
    }
}
