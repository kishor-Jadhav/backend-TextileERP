package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyCategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyCategoryMasterRepo extends JpaRepository<PartyCategoryMaster, Long> {
    PartyCategoryMaster findByPartyCategoryId(Long partyCategoryId);
}
