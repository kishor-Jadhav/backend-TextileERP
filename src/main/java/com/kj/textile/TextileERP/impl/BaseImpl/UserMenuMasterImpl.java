package com.kj.textile.TextileERP.impl.BaseImpl;

import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.Exceptions.ApplicationDataException;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuMasterModel;
import com.kj.textile.TextileERP.repo.BaseRepo.AppClientMasterRepo;
import com.kj.textile.TextileERP.repo.BaseRepo.UserMenuMasterRepo;
import com.kj.textile.TextileERP.services.BaseService.AppClientProjectMasterService;
import com.kj.textile.TextileERP.services.BaseService.UserMenuMasterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserMenuMasterImpl implements UserMenuMasterService {
    @Autowired
    UserMenuMasterRepo userMenuMasterRepo;

    @Autowired
    AppClientProjectMasterService appClientProjectMasterService;
    @Override
    public List<UserMenuMaster> getAllList() {
        return userMenuMasterRepo.findAll();
    }
    @Autowired
    AppClientMasterRepo appClientMasterRepo;
    @Override
    public UserMenuMasterModel saveForm(UserMenuMasterModel requestModelData) {
        UserMenuMaster entityData = new UserMenuMaster();
        UserContextDTO user = UserContext.get();
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(requestModelData.getClientProjectMaster().getAppClientProjectId());
        entityData.setMenuName(requestModelData.getMenuName());
        entityData.setMenuLevel(requestModelData.getMenuLevel());
        entityData.setMenuOrder(requestModelData.getMenuOrder());
        entityData.setParentMenuId(requestModelData.getParentMenuId());
        entityData.setClientProjectMaster(appClientProjectMaster);
        entityData.setMenuRoute(requestModelData.getMenuRoute());
        entityData.setNotDelete(requestModelData.isNotDelete());
        if( entityData.getUserAuditEntity() != null){
            entityData.getUserAuditEntity().setCreatedDate(LocalDateTime.now());
            entityData.getUserAuditEntity().setCreatedBy(user.getUsername());
        }

        entityData.setActive(true);
       // AppClientMaster AppCliententityData = appClientMasterRepo.findByAppClientId(requestModelData.getAppClientMaster().getAppClientId());
       // entityData.setAppClientMaster(AppCliententityData);
        userMenuMasterRepo.save(entityData);
        return requestModelData;
    }

    @Override
    public UserMenuMasterModel updateForm(UserMenuMasterModel requestModelData) {
        UserContextDTO user = UserContext.get();
        UserMenuMaster entityData =userMenuMasterRepo.findByMenuId(requestModelData.getMenuId());
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(requestModelData.getClientProjectMaster().getAppClientProjectId());
        entityData.setMenuName(requestModelData.getMenuName());
        entityData.setMenuLevel(requestModelData.getMenuLevel());
        entityData.setMenuOrder(requestModelData.getMenuOrder());
        entityData.setParentMenuId(requestModelData.getParentMenuId());
        entityData.setClientProjectMaster(appClientProjectMaster);
        entityData.setMenuRoute(requestModelData.getMenuRoute());
        entityData.setNotDelete(requestModelData.isNotDelete());
        entityData.setActive(true);
        if( entityData.getUserAuditEntity() != null){
            entityData.getUserAuditEntity().setUpdatedDate(LocalDateTime.now());
            entityData.getUserAuditEntity().setUpdatedBy(user.getUsername());
        }

        entityData.setClientProjectMaster(appClientProjectMaster);
       // AppClientMaster AppCliententityData = appClientMasterRepo.findByAppClientId(requestModelData.getAppClientMaster().getAppClientId());
       // entityData.setAppClientMaster(AppCliententityData);
        userMenuMasterRepo.save(entityData);
        return requestModelData;
    }
    @Transactional
    @Override
    public String deleteMenu(Long Id) {
        try{
            userMenuMasterRepo.deleteById(Id);

            return "success";
        } catch (Exception ex){
            throw new ApplicationDataException(ex.getMessage());
        }
    }
    @Override
    public UserMenuMaster getDataById(Long Id) {
        return userMenuMasterRepo.findByMenuId(Id);
    }
}
