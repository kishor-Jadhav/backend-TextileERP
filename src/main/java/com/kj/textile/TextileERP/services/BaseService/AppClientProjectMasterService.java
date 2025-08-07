package com.kj.textile.TextileERP.services.BaseService;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.AppClientProjectMasterModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppClientProjectMasterService {
    List<AppClientProjectMaster> getAllList();

    AppClientProjectMasterModel saveForm(AppClientProjectMasterModel requestModelData);
    AppClientProjectMasterModel updateForm(AppClientProjectMasterModel requestModelData);
    AppClientProjectMaster getDataById(Long Id);

    List<AppClientProjectMaster> fillProjectListByClientId(Long Id);
}
