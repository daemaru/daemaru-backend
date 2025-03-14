package com.demaru.domain.user.domain.persistence;

import com.demaru.domain.user.domain.Admin;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByAccountId(String accountId);

    boolean existsByAccountId(String accountId);
}
