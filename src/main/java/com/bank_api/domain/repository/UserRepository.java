package com.bank_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank_api.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccountNumber(String accountNumber);
}
