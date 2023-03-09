package com.hoaxify.repository;

import com.hoaxify.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByNickname(String nickname);
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByNickname(String nickname);

}
