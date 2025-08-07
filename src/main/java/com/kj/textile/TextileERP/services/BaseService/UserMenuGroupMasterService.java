package com.kj.textile.TextileERP.services.BaseService;

import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupMaster;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuGroupDetailMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuGroupMasterModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMenuGroupMasterService {
    List<UserMenuGroupMaster> getAllList();

    UserMenuGroupMasterModel saveForm(UserMenuGroupMasterModel requestModelData);
    UserMenuGroupMasterModel updateForm(UserMenuGroupMasterModel requestModelData);
    UserMenuGroupMasterModel getDataById(Long Id);

    List<UserMenuGroupDetailMasterModel> getGroupDetailForAdd();

    String deleteUserGroup(Long Id);
}
