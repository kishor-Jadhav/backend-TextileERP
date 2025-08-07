package com.kj.textile.TextileERP.controller;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.model.BaseModel.AppClientMasterModel;
import com.kj.textile.TextileERP.model.UserMaserModel;
import com.kj.textile.TextileERP.services.UserMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@ResponseBody
@RestController
@RequestMapping("/api/v2/")
public class AdminController {
    @Autowired
    UserMasterService userMasterService;

    //  @PreAuthorize("hasRole('ADMIN')")
    // @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("auth/getadmin")
    public List<UserMaser> getUserName(Authentication authentication) {
        System.out.println("Authenticated User: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        List<UserMaser> user = userMasterService.getAlluserList();
        return user;
    }

    @GetMapping("auth/getregisteruserlist")
    public List<UserMaserModel> getregisteruserlist(Authentication authentication) {

        List<UserMaserModel> user = userMasterService.getUserList();
        return user;
    }
	 @GetMapping("auth/getregisteruser/{Id}")
    public UserMaserModel getregisteruser(@PathVariable String Id) {

         UserMaserModel user = userMasterService.findUserById(Long.parseLong(Id));
        return user;
    }
    @PostMapping("auth/saveregisteruser")
    public UserMaser saveRegisterUserByAdmin(@RequestBody UserMaserModel requestModel){
        return userMasterService.registerUserByAdmin(requestModel);
    }
    @PostMapping("auth/updateregisteruser")
    public UserMaser updateRegisterUserByAdmin(@RequestBody UserMaserModel requestModel){
        return userMasterService.updateRegisterUserByAdmin(requestModel);
    }
}
