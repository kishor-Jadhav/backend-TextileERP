package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.FirmMaster;
import com.kj.textile.TextileERP.model.Master.CityMasterModel;
import com.kj.textile.TextileERP.model.Master.FirmMasterModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FirmMasaterService {
    List<FirmMaster> getAllFirmMasterList();

    FirmMaster saveFirmMaster(FirmMasterModel cityMasterModel);
    FirmMaster updateFirmMaster(FirmMasterModel cityMasterModel);
    FirmMaster getFirmData(Long cityId);
}
