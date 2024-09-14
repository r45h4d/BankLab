package az.ingress.dao.repository;

import az.ingress.dao.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.pin = :pin")
    CustomerEntity findByPIN(String pin);
}
