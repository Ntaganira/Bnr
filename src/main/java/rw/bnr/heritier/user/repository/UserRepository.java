package rw.bnr.heritier.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.bnr.heritier.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}