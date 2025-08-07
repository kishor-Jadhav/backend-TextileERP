package com.kj.textile.TextileERP.services.BaseService;

import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientProjectMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuMasterModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserMenuMasterService {
    List<UserMenuMaster> getAllList();

    UserMenuMasterModel saveForm(UserMenuMasterModel requestModelData);
    UserMenuMasterModel updateForm(UserMenuMasterModel requestModelData);

    @Transactional
    String deleteMenu(Long Id);

    UserMenuMaster getDataById(Long Id);
}
