package com.example.hackatonpractice_1.repository;

import com.example.hackatonpractice_1.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian,Long>{
    Optional<Guardian> findByEmail(String email);
    boolean existsByEmail(String email);
}
