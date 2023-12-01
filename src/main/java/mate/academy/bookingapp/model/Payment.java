package mate.academy.bookingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.net.URL;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE payments SET is_Deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "payments")
@Accessors(chain = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Status status;
    private Long bookingId;
    private URL sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;

    public enum Status {
        PENDING, CONFIRMED, CANCELED, EXPIRED
    }
}
