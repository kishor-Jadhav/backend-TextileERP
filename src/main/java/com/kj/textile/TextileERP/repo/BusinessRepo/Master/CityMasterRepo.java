package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityMasterRepo extends JpaRepository<CityMaster,Long> {

    CityMaster findByCityId(Long cityId);
}
