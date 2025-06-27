package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.YarnMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YarnMasterRepo extends JpaRepository<YarnMaster,Long > {
YarnMaster findByCountId (Long CountId);
}
