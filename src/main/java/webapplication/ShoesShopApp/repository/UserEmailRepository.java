package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.User;

import java.util.Optional;

@Repository
public interface UserEmailRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
