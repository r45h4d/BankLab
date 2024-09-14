package az.ingress.model.mapper;

import az.ingress.dao.entity.OfferEntity;
import az.ingress.model.dto.OfferDTO;

public class OfferMapper {
    public static OfferDTO buildOfferDTO(OfferEntity offer){
        return OfferDTO.builder().
                term(offer.getTerm()).
                interest(offer.getInterest()).
                monthlyPayment(offer.getMonthlyPayment()).
                amount(offer.getAmount()).
                accepted(offer.getAccepted()).
                createdAt(offer.getCreatedAt()).
                updatedAt(offer.getUpdatedAt()).
                build();
    }
}
