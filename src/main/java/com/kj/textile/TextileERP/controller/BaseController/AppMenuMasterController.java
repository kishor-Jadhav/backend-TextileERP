package com.kj.textile.TextileERP.controller.BaseController;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserAssignGroupEntity;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.UserAssignGroupModel;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuGroupMasterModel;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuMasterModel;
import com.kj.textile.TextileERP.services.BaseService.UserAssignGroupService;
import com.kj.textile.TextileERP.services.BaseService.UserMenuGroupMasterService;
import com.kj.textile.TextileERP.services.BaseService.UserMenuMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@ResponseBody
@RestController
@RequestMapping("/api/v2/auth/")
public class AppMenuMasterController {
    @Autowired
    UserMenuMasterService userMenuMasterService;

    @Autowired
    UserMenuGroupMasterService userMenuGroupMasterService;

    @Autowired
    UserAssignGroupService userAssignGroupService;

    @GetMapping("getMenulist")
    public List<UserMenuMaster> getAllData() {

        List<UserMenuMaster> dataList = userMenuMasterService.getAllList();
        return dataList;
    }
    @GetMapping("getMenu/{Id}")
    public UserMenuMaster getData(@PathVariable String Id) {
        UserMenuMaster data = userMenuMasterService.getDataById(Long.parseLong(Id));
        return data;
    }
    @PostMapping("saveMenu")
    public UserMenuMasterModel saveForm(@RequestBody UserMenuMasterModel requestModel){
        return userMenuMasterService.saveForm(requestModel);
    }
    @PostMapping("updateMenu")
    public UserMenuMasterModel updateForm(@RequestBody UserMenuMasterModel requestModel){
        return userMenuMasterService.updateForm(requestModel);
    }

    @GetMapping("deleteMenu/{Id}")
    public String deleteMenu(@PathVariable String Id) {
        String data = userMenuMasterService.deleteMenu(Long.parseLong(Id));
        return data;
    }

    //Group
    @GetMapping("getMenuGrouplist")
    public List<UserMenuGroupMaster> getAllGroupData() {

        List<UserMenuGroupMaster> dataList = userMenuGroupMasterService.getAllList();
        return dataList;
    }
    @GetMapping("getMenuGroup/{Id}")
    public UserMenuGroupMasterModel getGroupData(@PathVariable String Id) {
        UserMenuGroupMasterModel data = userMenuGroupMasterService.getDataById(Long.parseLong(Id));
        return data;
    }
    @PostMapping("saveMenuGroup")
    public UserMenuGroupMasterModel saveGroupForm(@RequestBody UserMenuGroupMasterModel requestModel){
        return userMenuGroupMasterService.saveForm(requestModel);
    }
    @PostMapping("updateMenuGroup")
    public UserMenuGroupMasterModel updateGroupForm(@RequestBody UserMenuGroupMasterModel requestModel){
        return userMenuGroupMasterService.updateForm(requestModel);
    }
    @GetMapping("deleteMenuGroup/{Id}")
    public String deleteGroupData(@PathVariable String Id) {
        String data = userMenuGroupMasterService.deleteUserGroup(Long.parseLong(Id));
        return data;
    }

    //User Assign Group
    @GetMapping("getuserassigngrouplist")
    public List<UserAssignGroupEntity> getUserGrouplist() {

        List<UserAssignGroupEntity> dataList = userAssignGroupService.getAllList();
        return dataList;
    }
    @GetMapping("getuserassigngroup/{Id}")
    public UserAssignGroupEntity getuserassigngroup(@PathVariable String Id) {
        UserAssignGroupEntity data = userAssignGroupService.getDataById(Long.parseLong(Id));
        return data;
    }
    @PostMapping("savegetuserassigngroup")
    public UserAssignGroupModel savegetuserassigngroup(@RequestBody UserAssignGroupModel requestModel){
        return userAssignGroupService.saveForm(requestModel);
    }
    @PostMapping("updategetuserassigngroup")
    public UserAssignGroupModel updategetuserassigngroup(@RequestBody UserAssignGroupModel requestModel){
        return userAssignGroupService.updateForm(requestModel);
    }
    @GetMapping("deletegetuserassigngroup/{Id}")
    public String deletegetuserassigngroup(@PathVariable String Id) {
        String data = userAssignGroupService.deleteRecordById(Long.parseLong(Id));
        return data;
    }
}
