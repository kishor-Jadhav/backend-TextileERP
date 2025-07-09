package com.kj.textile.TextileERP.controller;

import com.kj.textile.TextileERP.entity.UserMaser;
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
public class GeneralController {
    @Autowired
    UserMasterService userMasterService;

  //  @PreAuthorize("hasRole('USER')")
   // @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping("user/getuser")
    public List<UserMaser> getUserName(Authentication authentication) {
        System.out.println("Authenticated User: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        List<UserMaser> user = userMasterService.getAlluserList();
        return user;
    }
}
