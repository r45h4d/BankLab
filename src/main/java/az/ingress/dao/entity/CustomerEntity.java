package az.ingress.dao.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String pin;

    @Column(name = "full_name")
    String fullName;    //VARCHAR(128)

    @Column(name = "phone_number")
    String phoneNumber;     //VARCHAR(128)

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @OneToMany(mappedBy = "customer")
    private List<CreditEntity> creditList;
}
