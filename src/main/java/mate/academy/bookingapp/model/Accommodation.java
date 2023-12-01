package mate.academy.bookingapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE accommodations SET is_Deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "accommodations")
@Accessors(chain = true)
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Type cannot by null")
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id")
    private Address address;
    @NotNull(message = "Size cannot by null")
    private String size;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "accommodation_id"))
    @Column(nullable = false)
    private List<String> amenities = new ArrayList<>();
    @Min(0)
    private BigDecimal dailyRate;
    @Min(0)
    private Integer availability;
    @Column(nullable = false)
    private boolean isDeleted = false;

    public enum Type {
        HOUSE, APARTMENT, CONDO, VACATION_HOME
    }
}
