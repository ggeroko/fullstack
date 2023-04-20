package com.example.fullStackbackEnd.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<com.example.fullStackbackEnd.model.User,Long> {
}
