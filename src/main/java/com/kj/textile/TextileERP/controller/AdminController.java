package com.kj.textile.TextileERP.controller;

import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.services.UserMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
