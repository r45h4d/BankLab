package az.ingress.model.mapper;

import az.ingress.dao.entity.CreditEntity;
import az.ingress.dao.entity.CustomerEntity;
import az.ingress.model.dto.CreditDTO;

import java.time.LocalDate;

import static az.ingress.model.enums.CreditStatus.*;

public class CreditMapper {
    public static CreditEntity buildCreditEntity(CreditDTO credit){
        return CreditEntity.builder().
                term(credit.getTerm()).
                interest(credit.getInterest()).
                monthlyPayment(credit.getMonthlyPayment()).
                amount(credit.getAmount()).
                status(DRAFT.name()).
                createdAt(LocalDate.now()).
                customer(new CustomerEntity()).
                build();
    }

    public static CreditDTO buildCreditDTO(CreditEntity credit){
        return CreditDTO.builder().
                term(credit.getTerm()).
                interest(credit.getInterest()).
                monthlyPayment(credit.getMonthlyPayment()).
                amount(credit.getAmount()).
                build();
    }
}
