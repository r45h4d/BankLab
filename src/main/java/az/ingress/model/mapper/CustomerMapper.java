package az.ingress.model.mapper;

import az.ingress.dao.entity.CustomerEntity;
import az.ingress.model.dto.CustomerDTO;

import java.time.LocalDate;

public class CustomerMapper {
    public static CustomerEntity buildCustomerEntity(CustomerDTO customer){
        LocalDate now = LocalDate.now();
        return CustomerEntity.builder().
                pin(customer.getPin()).
                fullName(customer.getFullName()).
                phoneNumber(customer.getPhoneNumber()).
                createdAt(now).
                updatedAt(now).
                build();
    }

    public static CustomerDTO buildCustomerDTO(CustomerEntity customer){
        return CustomerDTO.builder().
                pin(customer.getPin()).
                fullName(customer.getFullName()).
                phoneNumber(customer.getPhoneNumber()).
                build();
    }
}
