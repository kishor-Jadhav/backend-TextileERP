package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.QualityMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityMasterRepo extends JpaRepository<QualityMaster, Long> {
QualityMaster findByQualityId(Long qualityId);
}
