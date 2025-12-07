package com.kj.textile.TextileERP.impl.BaseImpl;

import com.kj.textile.TextileERP.AppUtils.CommonUtils;
import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.entity.BaseEntity.UserAssignGroupEntity;

import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupMaster;
import com.kj.textile.TextileERP.entity.UserAuditEntity;
import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.entity.UserRoles;
import com.kj.textile.TextileERP.model.BaseModel.UserAssignGroupModel;
import com.kj.textile.TextileERP.repo.BaseRepo.UserAssignGroupRepo;
import com.kj.textile.TextileERP.repo.BaseRepo.UserMenuGroupMasterRepo;
import com.kj.textile.TextileERP.repo.UserMaserRepo;
import com.kj.textile.TextileERP.services.BaseService.UserAssignGroupService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAssignGroupImpl implements UserAssignGroupService {
    @Autowired
    UserAssignGroupRepo userAssignGroupRepo;

    @Autowired
    UserMaserRepo userMaserRepo;

    @Autowired
    UserMenuGroupMasterRepo userMenuGroupMasterRepo;

    @Override
    public List<UserAssignGroupEntity> getAllList() {
        CommonUtils utils = new CommonUtils();
        // Take Login User priority
//        UserContextDTO userContext = UserContext.get();
//        Optional<Integer> userRolePriorityOption = userContext.getRoles().stream().map(item->item.getRolePriority())
//                .findFirst();
//        int loginUserPriority =0;
//        if (userRolePriorityOption.isPresent()) {
//            loginUserPriority = userRolePriorityOption.get();
//
//        }
        int loginUserPriority =  utils.getLoginUserPriority();

        // Check User Has SuperId role
//        List<String> rolesToContains = List.of("ROLE_SUPERADMIN");
//        boolean has_ROLE_SUPERADMIN =  userContext.getRoles().stream()
//                .map(UserRoles::getRoleName)
//                .anyMatch(rolesToContains::contains);
//
        boolean has_ROLE_SUPERADMIN = utils.isUserSuperAdmin();
        List<UserAssignGroupEntity> list =  new ArrayList<>();
        List<UserAssignGroupEntity> result =userAssignGroupRepo.findAll();
        for(UserAssignGroupEntity data : result){
            boolean hasNoneOfTheExcludedRoles = false;

            // Get Record User Priority
            Optional<Integer> rolePriorityOption = data.getUserMaser().getRoles().stream().map(item->item.getRolePriority())
                    .findFirst();
            int priority =0;
            if (rolePriorityOption.isPresent()) {
                priority = rolePriorityOption.get();

            }

            if(has_ROLE_SUPERADMIN){
                hasNoneOfTheExcludedRoles = true;
            } else if (loginUserPriority<priority){
                hasNoneOfTheExcludedRoles = true;
            }
            if(hasNoneOfTheExcludedRoles) {
                UserAssignGroupEntity entity = new UserAssignGroupEntity();
                entity.setUserMenuGroupMaster(data.getUserMenuGroupMaster());
                entity.setUserMaser(data.getUserMaser());
                entity.setUserGroupAssignId(data.getUserGroupAssignId());
                entity.setUserAuditEntity(data.getUserAuditEntity());
                list.add(entity);
            }
        }
        return list;
    }

    @Override
    public UserAssignGroupModel saveForm(UserAssignGroupModel requestModelData) {
        UserAssignGroupEntity entityData = new UserAssignGroupEntity();
        UserContextDTO user = UserContext.get();
        UserMaser userMaser = userMaserRepo.findByUserId(requestModelData.getUserMaser().getUserId());
        entityData.setUserMaser(userMaser);
        UserMenuGroupMaster userMenuGroupMaster = userMenuGroupMasterRepo.findByUserMenuGroupId(requestModelData.getUserMenuGroupMaster().getUserMenuGroupId()) ;
        entityData.setUserMenuGroupMaster(userMenuGroupMaster);
        //Audit Entry
        if (entityData.getUserAuditEntity() == null) {
            entityData.setUserAuditEntity(new UserAuditEntity());
        }
        entityData.getUserAuditEntity().setCreatedBy(user.getUsername());
        entityData.getUserAuditEntity().setCreatedDate(LocalDateTime.now());


        userAssignGroupRepo.save(entityData);
        return requestModelData;
    }

    @Override
    public UserAssignGroupModel updateForm(UserAssignGroupModel requestModelData) {
        UserAssignGroupEntity entityData = userAssignGroupRepo.findByUserGroupAssignId(requestModelData.getUserGroupAssignId());
        UserContextDTO user = UserContext.get();
        UserMaser userMaser = userMaserRepo.findByUserId(requestModelData.getUserMaser().getUserId());
        entityData.setUserMaser(userMaser);
        UserMenuGroupMaster userMenuGroupMaster = userMenuGroupMasterRepo.findByUserMenuGroupId(requestModelData.getUserMenuGroupMaster().getUserMenuGroupId()) ;
        entityData.setUserMenuGroupMaster(userMenuGroupMaster);

        //Audit Entry
        if (entityData.getUserAuditEntity() == null) {
            entityData.setUserAuditEntity(new UserAuditEntity());
        }
        entityData.getUserAuditEntity().setUpdatedBy(user.getUsername());
        entityData.getUserAuditEntity().setUpdatedDate(LocalDateTime.now());

        userAssignGroupRepo.save(entityData);
        return requestModelData;
    }

    @Override
    public UserAssignGroupEntity getDataById(Long Id) {
        return userAssignGroupRepo.findByUserGroupAssignId(Id);
    }

    @Override
    public UserAssignGroupEntity findByUserMaser(Long Id) {
        return userAssignGroupRepo.findByUserMaser_UserId(Id);
    }

    @Transactional
    @Override
    public String deleteRecordById(Long Id) {
        try{
            userAssignGroupRepo.deleteById(Id);

            return "success";
        } catch (Exception ex){
            return "fail";
        }
    }
}
