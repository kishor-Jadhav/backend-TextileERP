package com.kj.textile.TextileERP.repo.BaseRepo;

import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMenuGroupDetailMasterRepo extends JpaRepository<UserMenuGroupDetailMaster,Long> {
    UserMenuGroupDetailMaster findByUserMenuGroupDetailId(Long Id);
   List<UserMenuGroupDetailMaster> findByUserMenuGroupId(Long Id);
    void  deleteAllByUserMenuGroupId(Long Id);

}
