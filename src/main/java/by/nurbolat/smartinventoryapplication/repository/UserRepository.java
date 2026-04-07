package by.nurbolat.smartinventoryapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import by.nurbolat.smartinventoryapplication.entity.security.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}