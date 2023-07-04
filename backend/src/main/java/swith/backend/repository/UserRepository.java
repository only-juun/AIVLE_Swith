package swith.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swith.backend.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findBySerialNumber(String serialNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByNickname(String nickname);

}
