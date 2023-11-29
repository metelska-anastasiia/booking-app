package mate.academy.bookingapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE addresses SET is_Deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "addresses")
@Accessors(chain = true)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String firstAddressLine;
    private String secondAddressLine;
    @NotNull
    @Column(name = "zip_code")
    private Integer zipcode;
    @Column(nullable = false)
    private boolean isDeleted = false;
}
