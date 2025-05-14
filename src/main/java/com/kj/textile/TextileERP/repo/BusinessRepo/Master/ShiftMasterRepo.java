package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.ShiftMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftMasterRepo extends JpaRepository<ShiftMaster,Long> {
    ShiftMaster findByShiftId(Long shiftId);
}
