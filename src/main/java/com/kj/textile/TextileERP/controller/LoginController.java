package com.kj.textile.TextileERP.controller;

import com.kj.textile.TextileERP.Exceptions.InvalidPasswordException;
import com.kj.textile.TextileERP.Exceptions.UserNotFoundException;
import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.model.PasswordModel;
import com.kj.textile.TextileERP.model.UserMaserModel;
import com.kj.textile.TextileERP.security.JwtHelper;
import com.kj.textile.TextileERP.services.UserMasterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@ResponseBody
@RestController
@RequestMapping("/auth")
public class LoginController {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserMasterService userMasterService;

    @Autowired
    private JwtHelper helper;
    LoginController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
//    {
//        "userName":"superadmin",
//            "password":"admin2025",
//            "email":"superadmin@gmail.com",
//            "userRole":"superadmin"
//
//    }
    @PostMapping("loginuser")
    public UserMaserModel LoginUser(@RequestBody UserMaserModel userMaserModel) {
        UserMaser user = userMasterService.findByEmail(userMaserModel.getEmail());
        
        boolean isValidateUser= false;
        if (user != null) {
            boolean isPassMatch = passwordEncoder.matches(userMaserModel.getPassword(),user.getPassword());
            if(isPassMatch){
                isValidateUser = true;


                String token = this.helper.generateToken(user);
                userMaserModel.setValidateUser(isValidateUser);
                userMaserModel.setAuthToken(token);
                return userMaserModel;
            } else {
                throw new InvalidPasswordException("Password does not match");
            }

        } else {
            throw new UserNotFoundException("Email ID not found");
        }

    }

    
}
