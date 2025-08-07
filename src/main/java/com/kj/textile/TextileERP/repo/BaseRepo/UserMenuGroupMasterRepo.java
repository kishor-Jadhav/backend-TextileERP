package com.kj.textile.TextileERP.repo.BaseRepo;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserMenuGroupMasterRepo extends JpaRepository<UserMenuGroupMaster,Long> {
    UserMenuGroupMaster findByUserMenuGroupId(Long Id);
    @Query("Select Max(t.userMenuGroupId) from UserMenuGroupMaster as t")
    Long findMaxId();
}
