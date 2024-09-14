package az.ingress.controller;

import az.ingress.model.dto.CustomerDTO;
import az.ingress.service.concrete.CustomerServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceHandler customerService;
    @PostMapping("/customers")
    @ResponseStatus(CREATED)
    public void saveCustomer(@RequestBody CustomerDTO customer){
        customerService.saveCustomer(customer);
    }

    @GetMapping("/customers/{pin}")
    @ResponseStatus(OK)
    public CustomerDTO getCustomerByPIN(@PathVariable String pin){
        return customerService.getCustomerByPIN(pin);
    }
}