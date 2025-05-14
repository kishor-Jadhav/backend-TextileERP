package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyMasterRepo extends JpaRepository<PartyMaster,Long> {
    PartyMaster findByPartyId(Long partyId);
}
