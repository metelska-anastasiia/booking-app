package mate.academy.bookingapp.repository;

import java.util.Optional;
import mate.academy.bookingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
