package az.ingress.dao.entity;

import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    Integer term;

    BigDecimal interest;    //NUMERIC(16,2)

    @Column(name = "monthly_payment")
    BigDecimal monthlyPayment;  //NUMERIC(16,2)

    BigDecimal amount;  //NUMERIC(16,2)

    Boolean accepted;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @ManyToOne
    CreditEntity credit;
}
