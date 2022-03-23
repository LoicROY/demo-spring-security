package com.diginamic.security.demosecurity.repositories;

import com.diginamic.security.demosecurity.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.username = :name")
    Optional<User> findUserWithUsername(String name);
    Optional<User> findByUsername(String username);
}
