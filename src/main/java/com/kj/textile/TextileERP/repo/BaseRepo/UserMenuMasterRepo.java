package com.kj.textile.TextileERP.repo.BaseRepo;

import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMenuMasterRepo extends JpaRepository<UserMenuMaster,Long> {
    UserMenuMaster findByMenuId(Long Id);
}
