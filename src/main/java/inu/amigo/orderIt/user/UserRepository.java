package inu.amigo.orderIt.user;

import inu.amigo.orderIt.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRefreshToken(String refreshToken);
    Optional<User> findByUsername(String username);
}
