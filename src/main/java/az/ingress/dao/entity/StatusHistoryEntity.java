package az.ingress.dao.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="status_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusHistoryEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String status;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @ManyToOne
    CreditEntity credit;
}
