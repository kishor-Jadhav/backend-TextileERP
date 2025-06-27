package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyCategoryMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.YarnMaster;
import com.kj.textile.TextileERP.model.Master.PartyMasterModel;
import com.kj.textile.TextileERP.model.Master.YarnMasterModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface YarnMasterService {
    List<YarnMaster> getAllCount();



    YarnMaster getCountData(Long CountId);

    YarnMaster saveYarnMaster(YarnMasterModel yarnMasterModel);

    YarnMaster updateYarnMaster(YarnMasterModel yarnMasterModel);
}
