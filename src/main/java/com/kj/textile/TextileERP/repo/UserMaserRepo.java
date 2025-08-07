package com.kj.textile.TextileERP.repo;

import com.kj.textile.TextileERP.entity.UserMaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMaserRepo extends JpaRepository<UserMaser,Long> {

    UserMaser findByEmail(String email);
    UserMaser findByUserId(Long Id);
    UserMaser findByAuthUserName(String username);


}
