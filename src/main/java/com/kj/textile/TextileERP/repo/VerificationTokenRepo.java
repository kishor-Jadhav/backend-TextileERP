package com.kj.textile.TextileERP.repo;

import com.kj.textile.TextileERP.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);
}
