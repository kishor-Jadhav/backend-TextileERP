package com.kj.textile.TextileERP.controller.BaseController;

import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.model.UserMaserModel;
import com.kj.textile.TextileERP.services.BaseService.UserMenuGroupDetailMasterService;
import com.kj.textile.TextileERP.services.UserMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@ResponseBody
@RestController
@RequestMapping("/api/v1/")
public class UserConfigController {
    @Autowired
    UserMasterService userMasterService;

    @Autowired
    UserMenuGroupDetailMasterService userMenuGroupDetailMasterService;
    @GetMapping("getuserconfig")
    public UserMaserModel getUserConfigData() {
        UserContextDTO user = UserContext.get();
        UserMaserModel userData = userMasterService.getUserConfigData(user.getAuditEntryUserId());
        return userData;
    }
    @GetMapping("getusermenuconfiglist")
    public List<UserMenuGroupDetailMaster> getUserMenuList(@PathVariable String groupId) {

        List<UserMenuGroupDetailMaster> UserMenuGroupDetailMaster = userMenuGroupDetailMasterService.getDetailList(Long.parseLong(groupId));
        return UserMenuGroupDetailMaster;
    }
}
