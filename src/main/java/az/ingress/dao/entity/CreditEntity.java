package az.ingress.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="credits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Integer term;

    BigDecimal interest;    //NUMERIC(16,2)

    @Column(name = "monthly_payment")
    BigDecimal monthlyPayment; //NUMERIC(16,2)

    BigDecimal amount; //NUMERIC(16,2)

    String status; //VARCHAR(32)

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @JsonIgnore
    @ManyToOne
    CustomerEntity customer;

    @JsonIgnore
    @OneToMany(mappedBy = "credit")
    private List<OfferEntity> offers;

    @OneToMany(mappedBy = "credit")
    private List<StatusHistoryEntity> statusHistories;
}
