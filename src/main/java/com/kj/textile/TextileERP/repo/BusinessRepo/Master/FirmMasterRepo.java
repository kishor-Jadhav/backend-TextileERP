package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.FirmMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmMasterRepo extends JpaRepository<FirmMaster,Long> {
    FirmMaster findByFirmId(Long firmId);
}
