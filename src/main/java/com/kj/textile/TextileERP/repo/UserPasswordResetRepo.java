package com.kj.textile.TextileERP.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kj.textile.TextileERP.entity.UserPasswordReset;

@Repository
public interface UserPasswordResetRepo extends JpaRepository<UserPasswordReset,Long> {

    UserPasswordReset findByToken(String token);

     
    
}
