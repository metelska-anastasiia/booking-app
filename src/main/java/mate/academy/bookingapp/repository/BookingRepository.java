package mate.academy.bookingapp.repository;

import mate.academy.bookingapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
