package com.kj.textile.TextileERP.impl.BaseImpl;

import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
import com.kj.textile.TextileERP.entity.UserAuditEntity;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuGroupDetailMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuGroupMasterModel;
import com.kj.textile.TextileERP.repo.BaseRepo.AppClientMasterRepo;
import com.kj.textile.TextileERP.repo.BaseRepo.UserMenuGroupMasterRepo;
import com.kj.textile.TextileERP.repo.BaseRepo.UserMenuMasterRepo;
import com.kj.textile.TextileERP.services.BaseService.AppClientProjectMasterService;
import com.kj.textile.TextileERP.services.BaseService.UserMenuGroupDetailMasterService;
import com.kj.textile.TextileERP.services.BaseService.UserMenuGroupMasterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMenuGroupMasterImpl implements UserMenuGroupMasterService {
    @Autowired
    UserMenuGroupMasterRepo userMenuGroupMasterRepo;
    @Autowired
    AppClientProjectMasterService appClientProjectMasterService;
    @Autowired
    UserMenuGroupDetailMasterService userMenuGroupDetailMasterService;

    @Autowired
    AppClientMasterRepo appClientMasterRepo;

    @Autowired
    UserMenuMasterRepo userMenuMasterRepo;
    @Override
    public List<UserMenuGroupMaster> getAllList() {
        return userMenuGroupMasterRepo.findAll();
    }

    @Override
    public UserMenuGroupMasterModel saveForm(UserMenuGroupMasterModel requestModelData) {
        UserMenuGroupMaster entityData = new UserMenuGroupMaster();
        UserContextDTO user = UserContext.get();

        entityData.setUserGroupName(requestModelData.getUserGroupName());
        entityData.setActiveMenuGroup(true);

        AppClientMaster AppCliententityData = appClientMasterRepo.findByAppClientId(requestModelData.getAppClientMaster().getAppClientId());
         entityData.setAppClientMaster(AppCliententityData);
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(requestModelData.getAppClientProjectMaster().getAppClientProjectId());
        entityData.setAppClientProjectMaster(appClientProjectMaster);

        if (entityData.getUserAuditEntity() == null) {
            entityData.setUserAuditEntity(new UserAuditEntity());
        }
        entityData.getUserAuditEntity().setCreatedDate(LocalDateTime.now());
        entityData.getUserAuditEntity().setCreatedBy(user.getUsername());

        userMenuGroupMasterRepo.save(entityData);
        Long Id = userMenuGroupMasterRepo.findMaxId();
        List<UserMenuGroupDetailMasterModel> detailsModel = requestModelData.getUserMenuGroupDetailMasterModel();

        userMenuGroupDetailMasterService.saveForm(detailsModel,Id);
        return requestModelData;
    }

    @Override
    public UserMenuGroupMasterModel updateForm(UserMenuGroupMasterModel requestModelData) {
        UserMenuGroupMaster entityData = userMenuGroupMasterRepo.findByUserMenuGroupId(requestModelData.getUserMenuGroupId());
        UserContextDTO user = UserContext.get();

        entityData.setUserGroupName(requestModelData.getUserGroupName());
        entityData.setActiveMenuGroup(true);
        AppClientMaster AppCliententityData = appClientMasterRepo.findByAppClientId(requestModelData.getAppClientMaster().getAppClientId());
        entityData.setAppClientMaster(AppCliententityData);
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(requestModelData.getAppClientProjectMaster().getAppClientProjectId());
        entityData.setAppClientProjectMaster(appClientProjectMaster);

        //Audit Data
        if (entityData.getUserAuditEntity() == null) {
            entityData.setUserAuditEntity(new UserAuditEntity());
        }
        entityData.getUserAuditEntity().setUpdatedDate(LocalDateTime.now());
        entityData.getUserAuditEntity().setUpdatedBy(user.getUsername());
        userMenuGroupMasterRepo.save(entityData);
        List<UserMenuGroupDetailMasterModel> detailsModel = requestModelData.getUserMenuGroupDetailMasterModel();

        userMenuGroupDetailMasterService.saveForm(detailsModel,requestModelData.getUserMenuGroupId());
        return requestModelData;
    }

    @Override
    public UserMenuGroupMasterModel getDataById(Long Id) {
        UserMenuGroupMaster entityData = userMenuGroupMasterRepo.findByUserMenuGroupId(Id);
        UserMenuGroupMasterModel modelData = new UserMenuGroupMasterModel();
        modelData.setUserGroupName(entityData.getUserGroupName());
        modelData.setActiveMenuGroup(entityData.isActiveMenuGroup());
        AppClientMaster AppCliententityData = appClientMasterRepo.findByAppClientId(entityData.getAppClientMaster().getAppClientId());
        modelData.setAppClientMaster(AppCliententityData);
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(entityData.getAppClientProjectMaster().getAppClientProjectId());
        modelData.setAppClientProjectMaster(appClientProjectMaster);
        modelData.setUserAuditEntity(entityData.getUserAuditEntity());
        List<UserMenuGroupDetailMaster> userMenuGroupDetailMaster =  userMenuGroupDetailMasterService.getDetailList(entityData.getUserMenuGroupId());
        modelData.setUserMenuGroupDetailMasterModel(userMenuGroupDetailMasterService.getModelObject(userMenuGroupDetailMaster,Id));
        return modelData;
    }
    @Override
    public List<UserMenuGroupDetailMasterModel> getGroupDetailForAdd() {
        List<UserMenuMaster> userMenuMaster = userMenuMasterRepo.findAll();
        List<UserMenuGroupDetailMasterModel> list = new ArrayList<>();

       for(UserMenuMaster entityData:userMenuMaster){
           UserMenuGroupDetailMasterModel model = new UserMenuGroupDetailMasterModel();
           model.setUserMenuMaster(entityData);
           model.setAdd(false);
           model.setEdit(false);
           model.setDelete(false);
           model.setView(false);
           list.add(model);
       }
        return list;
    }
    @Transactional
    @Override
    public String deleteUserGroup(Long Id) {
        try{
            userMenuGroupDetailMasterService.deleteUserGroupDetail(Id);
            userMenuGroupMasterRepo.deleteById(Id);
            return "success";
        } catch (Exception ex){
            return "fail";
        }

    }
}
