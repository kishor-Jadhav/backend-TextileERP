package com.kj.textile.TextileERP.services.BaseService;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientMasterModel;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface AppClientMasterService   {
    List<AppClientMaster> getAllList();

    AppClientMaster saveForm(AppClientMasterModel requestModelData);
    AppClientMaster updateForm(AppClientMasterModel requestModelData);
    AppClientMaster getDataById(Long Id);
}
