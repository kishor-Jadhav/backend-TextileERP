package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyCategoryMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyMaster;
import com.kj.textile.TextileERP.model.Master.PartyMasterModel;

import java.util.List;

public interface PartyMasterService {
    List<PartyCategoryMaster> getPartyCategory();

    List<PartyMaster> getAllPartyMasterList();

    PartyMaster getPartyData(Long partyId);

    PartyMaster savePartyMaster(PartyMasterModel partyMasterModel);

    PartyMaster updatePartyMaster(PartyMasterModel partyMasterModel);
}
