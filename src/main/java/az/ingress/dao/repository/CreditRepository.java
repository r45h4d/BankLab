package az.ingress.dao.repository;

import az.ingress.dao.entity.CreditEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {
    @EntityGraph(attributePaths = "statusHistories")
    @Query(value = "SELECT c FROM CreditEntity c WHERE c.id = :id")
    CreditEntity findCreditWithStatusHistoriesById(Long id);

    List<CreditEntity> findByStatus(String status);
}
