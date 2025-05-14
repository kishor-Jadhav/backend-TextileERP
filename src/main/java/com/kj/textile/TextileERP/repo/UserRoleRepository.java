package com.kj.textile.TextileERP.repo;

import com.kj.textile.TextileERP.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
    Optional<UserRoles> findByRoleName(String name);
}
