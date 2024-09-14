package az.ingress.dao.repository;

import az.ingress.dao.entity.StatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistoryEntity, Long> {
    @Modifying
    @Query("UPDATE StatusHistoryEntity sh SET sh.status = :status WHERE sh.credit.id = :creditId")
    void updateStatusHistoriesByCreditId(@Param("status") String status, @Param("creditId") Long creditId);

}
