package com.kj.textile.TextileERP.impl.BaseImpl;

import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuGroupDetailMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuMasterModel;
import com.kj.textile.TextileERP.repo.BaseRepo.UserMenuGroupDetailMasterRepo;
import com.kj.textile.TextileERP.repo.BaseRepo.UserMenuMasterRepo;
import com.kj.textile.TextileERP.services.BaseService.UserMenuGroupDetailMasterService;
import com.kj.textile.TextileERP.services.BaseService.UserMenuMasterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserMenuGroupDetailMasterImpl implements UserMenuGroupDetailMasterService {
    @Autowired
    UserMenuGroupDetailMasterRepo userMenuGroupDetailMasterRepo;

    @Autowired
    UserMenuMasterRepo userMenuMasterRepo;

    @Autowired
    UserMenuMasterService userMenuMasterService;
    @Override
    public List<UserMenuGroupDetailMaster> getAllList() {
        return userMenuGroupDetailMasterRepo.findAll();
    }
    @Transactional
    @Override
    public String saveForm(List<UserMenuGroupDetailMasterModel> userMenuGroupDetailMasterModel, Long Id) {
        List<UserMenuGroupDetailMaster> list = new ArrayList<>();
        userMenuGroupDetailMasterRepo.deleteAllByUserMenuGroupId(Id);
        for(UserMenuGroupDetailMasterModel modelData : userMenuGroupDetailMasterModel){
            UserMenuMaster userMenuMaster = userMenuMasterRepo.findById(modelData.getUserMenuMaster().getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu name not found"));
            UserMenuGroupDetailMaster detailsData = new UserMenuGroupDetailMaster();
            detailsData.setUserMenuGroupId(Id);
            userMenuMaster.setActive(true);

            detailsData.setUserMenuMaster(userMenuMaster);
            detailsData.setAdd(modelData.isAdd());
            detailsData.setEdit(modelData.isEdit());
            detailsData.setDelete(modelData.isDelete());
            detailsData.setView(modelData.isView());


            list.add(detailsData);

        }
        userMenuGroupDetailMasterRepo.saveAll(list);

        return "OK";
    }
    @Override
    public List<UserMenuGroupDetailMasterModel> getModelObject(List<UserMenuGroupDetailMaster> userMenuGroupDetailMaster, Long Id) {
        List<UserMenuGroupDetailMasterModel> list = new ArrayList<>();

        for(UserMenuGroupDetailMaster respData : userMenuGroupDetailMaster){
            UserMenuMaster userMenuMaster = userMenuMasterRepo.findById(respData.getUserMenuMaster().getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu name not found"));
            UserMenuGroupDetailMasterModel detailsData = new UserMenuGroupDetailMasterModel();
            detailsData.setUserMenuGroupId(Id);
            detailsData.setUserMenuMaster(userMenuMaster);
            detailsData.setAdd(respData.isAdd());
            detailsData.setEdit(respData.isEdit());
            detailsData.setDelete(respData.isDelete());
            detailsData.setView(respData.isView());

            list.add(detailsData);

        }
        return list;
    }
    @Transactional
    @Override
    public String saveDetailForm(List<UserMenuGroupDetailMaster> userMenuGroupDetailMaster, Long Id) {
        for(UserMenuGroupDetailMaster entity:userMenuGroupDetailMaster){
            entity.setUserMenuGroupId(Id);
        }
        userMenuGroupDetailMasterRepo.deleteById(Id);
        userMenuGroupDetailMasterRepo.saveAll(userMenuGroupDetailMaster);
        return "OK";
    }

    @Transactional
    @Override
    public String updateDetailForm(List<UserMenuGroupDetailMaster> userMenuGroupDetailMaster, Long Id) {
        userMenuGroupDetailMasterRepo.deleteById(Id);
        for(UserMenuGroupDetailMaster entity:userMenuGroupDetailMaster){
            entity.setUserMenuGroupId(Id);
        }
        userMenuGroupDetailMasterRepo.saveAll(userMenuGroupDetailMaster);
        return "OK";
    }


    @Override
    public UserMenuGroupDetailMasterModel updateForm(UserMenuGroupDetailMasterModel requestModelData) {
        return null;
    }


    @Override
    public UserMenuGroupDetailMaster getDataById(Long Id) {

        return userMenuGroupDetailMasterRepo.findByUserMenuGroupDetailId(Id);
    }

    @Override
    public List<UserMenuGroupDetailMaster> getDetailList(Long Id) {
        return userMenuGroupDetailMasterRepo.findByUserMenuGroupId(Id);
    }

    @Override
    public List<UserMenuMasterModel> getMenuDetailList(Long Id, String userRoll ) {
        UserContextDTO user = UserContext.get();
        List<UserMenuMasterModel> menuList = new  ArrayList<>();
        if(Objects.equals(userRoll, "ROLE_SUPERADMIN")){
            List<UserMenuMaster> dataList = userMenuMasterService.getAllList();
            for(UserMenuMaster list: dataList){
                UserMenuMasterModel userMenuMasterModel = new UserMenuMasterModel();
                userMenuMasterModel.setMenuId(list.getMenuId());
                userMenuMasterModel.setMenuName(list.getMenuName());
                userMenuMasterModel.setAdd(true);
                userMenuMasterModel.setEdit(true);
                userMenuMasterModel.setDelete(true);
                userMenuMasterModel.setMenuOrder(list.getMenuOrder());
                userMenuMasterModel.setMenuLevel(list.getMenuLevel());
                userMenuMasterModel.setMenuRoute(list.getMenuRoute());
                userMenuMasterModel.setParentMenuId(list.getParentMenuId());
                menuList.add(userMenuMasterModel);
            }
            return menuList;
        }
        List<UserMenuGroupDetailMaster> MenuGroupDetailList =  userMenuGroupDetailMasterRepo.findByUserMenuGroupId(Id);
        for(UserMenuGroupDetailMaster list: MenuGroupDetailList) {
            if (list.isView())
            {
            UserMenuMasterModel userMenuMasterModel = new UserMenuMasterModel();
            userMenuMasterModel.setMenuId(list.getUserMenuMaster().getMenuId());
            userMenuMasterModel.setMenuName(list.getUserMenuMaster().getMenuName());
            userMenuMasterModel.setAdd(list.isAdd());
            userMenuMasterModel.setEdit(list.isEdit());
            userMenuMasterModel.setDelete(list.isDelete());
            userMenuMasterModel.setMenuOrder(list.getUserMenuMaster().getMenuOrder());
            userMenuMasterModel.setMenuLevel(list.getUserMenuMaster().getMenuLevel());
            userMenuMasterModel.setMenuRoute(list.getUserMenuMaster().getMenuRoute());
            userMenuMasterModel.setParentMenuId(list.getUserMenuMaster().getParentMenuId());
            menuList.add(userMenuMasterModel);
         }
        }

        return menuList;
    }
    @Transactional
    @Override
    public String deleteUserGroupDetail(Long Id) {
        try{
            userMenuGroupDetailMasterRepo.deleteAllByUserMenuGroupId(Id);

            return "success";
        } catch (Exception ex){
            return "fail";
        }

    }
}
