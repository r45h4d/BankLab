package az.ingress.service.concrete;

import az.ingress.client.OfferClient;
import az.ingress.dao.entity.CreditEntity;
import az.ingress.dao.entity.CustomerEntity;
import az.ingress.dao.entity.OfferEntity;
import az.ingress.dao.entity.StatusHistoryEntity;
import az.ingress.dao.repository.CreditRepository;
import az.ingress.dao.repository.CustomerRepository;
import az.ingress.dao.repository.OfferRepository;
import az.ingress.dao.repository.StatusHistoryRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.dto.*;
import az.ingress.model.enums.CreditStatus;
import az.ingress.model.mapper.CreditMapper;
import az.ingress.model.mapper.OfferMapper;
import az.ingress.model.mapper.StatusHistoryMapper;
import az.ingress.service.abstraction.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.exception.ExceptionConstants.*;

@Service
@RequiredArgsConstructor
public class CreditServiceHandler implements CreditService {
    private final CreditRepository creditRepository;
    private final OfferClient offerClient;
    private final OfferRepository offerRepository;
    private final CustomerRepository customerRepository;
    private final StatusHistoryRepository statusHistoryRepository;

    public void updateCreditStatus(){
        List<CreditEntity> credits = creditRepository.findByStatus("DRAFT");

        credits.stream()
                .peek(credit -> {
                    credit.setStatus("EXPIRED");
                    credit.setUpdatedAt(LocalDate.now());
                })
                .forEach(creditRepository::save);
    }

    @Transactional
    public void acceptCredit(Long id, CreditStatus status){
        CreditEntity credit = fetchCreditIfExist(id);
        credit.setStatus(status.name());
        creditRepository.save(credit);
        statusHistoryRepository.updateStatusHistoriesByCreditId(status.name(), credit.getId());
        offerClient.setStatus(CreditStatusDTO.builder().productId(id).status(status.name()).build());
    }

    @Transactional
    public void rejectCredit(Long id, CreditStatus status){
        CreditEntity credit = fetchCreditIfExist(id);
        credit.setStatus(status.name());
        creditRepository.save(credit);
        statusHistoryRepository.updateStatusHistoriesByCreditId(status.name(), credit.getId());
        offerClient.setStatus(CreditStatusDTO.builder().productId(id).status(status.name()).build());
    }

    public List<OfferDTO> getOffer(){
        List<OfferEntity> offerEntities = offerRepository.findAll();

        return offerEntities.stream().map(
                offer -> {
                    if(offer.getAmount().compareTo(BigDecimal.valueOf(300))<0){
                        offer.setAmount(BigDecimal.valueOf(300));
                    }
                    return OfferMapper.buildOfferDTO(offer);
                }).toList();
    }


    public CreditWithHistoryDTO getCreditWithStatusHistoriesById(Long id){
        CreditEntity creditEntity = creditRepository.findCreditWithStatusHistoriesById(id);
        CreditDTO creditDTO = CreditMapper.buildCreditDTO(creditEntity);
        List<StatusHistoryDTO> statusHistoryDTOs = creditEntity.getStatusHistories().stream().map(StatusHistoryMapper::buildStatusHistoryDTO).toList();
        return CreditWithHistoryDTO.builder().credit(creditDTO).statusHistories(statusHistoryDTOs).build();
    }

    @Transactional
    @Override
    public List<ConveyorDTO> initializeCredit(CreditDTO credit, Long customerId) {
        CreditEntity creditEntity = CreditMapper.buildCreditEntity(credit);
        CustomerEntity customer = fetchCustomerIfExist(customerId);
        creditEntity.setCustomer(customer);
        creditRepository.save(creditEntity);
        List<ConveyorDTO> offers = offerClient.getOffers(credit);
        saveOffers(offers, creditEntity);
        saveStatusHistory(creditEntity);
        return offers;
    }

    private void saveStatusHistory(CreditEntity credit){
        LocalDate now = LocalDate.now();
        StatusHistoryEntity statusHistoryEntity = StatusHistoryEntity.builder().
                status(credit.getStatus()).
                createdAt(now).
                updatedAt(now).
                credit(credit).
                build();
        statusHistoryRepository.save(statusHistoryEntity);
    }

    private void saveOffers(List<ConveyorDTO> offers, CreditEntity creditEntity){
        LocalDate now = LocalDate.now();

        List<OfferEntity> offerEntities = offers.stream()
                .flatMap(response -> response.getProducts().stream()
                        .map(product -> OfferEntity.builder()
                                .term(product.getTerm())
                                .interest(BigDecimal.valueOf(product.getInterest()))
                                .monthlyPayment(BigDecimal.valueOf(product.getMonthlyPayment()))
                                .amount(BigDecimal.valueOf(product.getAmount()))
                                .accepted(false)
                                .createdAt(now)
                                .updatedAt(now)
                                .credit(creditEntity)
                                .build()
                        )
                )
                .collect(Collectors.toList());

        offerRepository.saveAll(offerEntities);
    }

    private CustomerEntity fetchCustomerIfExist(Long id){
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException(CUSTOMER_NOT_FOUND_MESSAGE, CLIENT_EXCEPTION_CODE));
    }

    private CreditEntity fetchCreditIfExist(Long id){
        return creditRepository.findById(id).orElseThrow(()-> new NotFoundException(CREDIT_NOT_FOUND_MESSAGE, CREDIT_NOT_FOUND_CODE));
    }
}
