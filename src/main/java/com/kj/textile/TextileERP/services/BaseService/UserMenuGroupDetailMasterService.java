package com.kj.textile.TextileERP.services.BaseService;

import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientProjectMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuGroupDetailMasterModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserMenuGroupDetailMasterService {
    List<UserMenuGroupDetailMaster> getAllList();


    @Transactional
    String saveForm(List<UserMenuGroupDetailMasterModel> userMenuGroupDetailMasterModel, Long Id);

    List<UserMenuGroupDetailMasterModel> getModelObject(List<UserMenuGroupDetailMaster> userMenuGroupDetailMaster, Long Id);

    @Transactional
    String saveDetailForm(List<UserMenuGroupDetailMaster> userMenuGroupDetailMaster, Long Id);

    UserMenuGroupDetailMasterModel updateForm(UserMenuGroupDetailMasterModel requestModelData);

    String updateDetailForm(List<UserMenuGroupDetailMaster> userMenuGroupDetailMaster, Long Id);

    UserMenuGroupDetailMaster getDataById(Long Id);
    List<UserMenuGroupDetailMaster> getDetailList(Long Id);

    @Transactional
    String deleteUserGroupDetail(Long Id);
}
