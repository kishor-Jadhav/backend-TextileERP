package com.kj.textile.TextileERP.repo.BaseRepo;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserAssignGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAssignGroupRepo extends JpaRepository<UserAssignGroupEntity, Long> {
    UserAssignGroupEntity findByUserGroupAssignId(Long Id);
    UserAssignGroupEntity findByUserMaser_UserId(Long Id);
}
