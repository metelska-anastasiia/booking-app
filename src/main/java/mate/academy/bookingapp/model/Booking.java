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
import java.time.LocalDate;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE bookings SET is_Deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "bookings")
@Accessors(chain = true)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate checkInTime;
    private LocalDate checkOutTime;
    private Long accommodationId;
    private Long userId;
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Status status;

    public enum Status {
        PENDING, CONFIRMED, CANCELED, EXPIRED
    }
}
