package az.ingress.client;

import az.ingress.client.decoder.CustomErrorDecoder;
import az.ingress.model.dto.ConveyorDTO;
import az.ingress.model.dto.CreditDTO;
import az.ingress.model.dto.CreditStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-employee", url = "https://polar-lowlands-53572-4147c5b66e05.herokuapp.com", configuration = CustomErrorDecoder.class)
public interface OfferClient {
    @GetMapping("/v1/credit-conveyor/offers")
    List<ConveyorDTO> getOffers(@RequestBody CreditDTO credit);

    @PutMapping("/v1/credit-conveyor/offers?conveyorId=1")
    void setStatus(@RequestBody CreditStatusDTO creditStatus);
}
