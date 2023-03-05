package dev.rashad.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.rashad.springboot.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {  
  Optional<User> findByEmail(String email);

}
