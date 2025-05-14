package com.kj.textile.TextileERP.controller;

import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.entity.VerificationToken;
import com.kj.textile.TextileERP.events.RegistrationCompleteEvent;
import com.kj.textile.TextileERP.model.PasswordModel;
import com.kj.textile.TextileERP.model.UserMaserModel;
import com.kj.textile.TextileERP.services.UserMasterService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@RestController
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserMasterService userMasterService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    RegistrationController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public String registration(@RequestBody UserMaserModel userMaserModel, HttpServletRequest request) {
        UserMaser user = userMasterService.registerUser(userMaserModel);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user,
                applicationUrl(request)));
        return "Success";
    }

    @GetMapping("/verificationToken")
    public String verificationToken(@RequestParam("token") String token) {
        System.out.println("Received token: " + token); // Debugging log
        String res = userMasterService.validateVerificationToken(token);
        if (res.equalsIgnoreCase("valid")) {
            return "Success validate";
        }
        return "unSuccess validate";
    }

    @GetMapping("/resendtoken")
    public String resendvrificationToken(@RequestParam("token") String token, HttpServletRequest request) {
        VerificationToken verificationToken = userMasterService.getNewToken(token);
        UserMaser user = verificationToken.getUserMaser();
        resendverificationToken(user, applicationUrl(request), token);
        return "";
    }

    @PostMapping("resetpassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        UserMaser user = userMasterService.findByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userMasterService.createNewTokenForResetPassword(user, token);
            url = passwordresetTokenEmail(user, applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("savepassword")
    public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel,
            HttpServletRequest request) {
        String result = userMasterService.validatePassResetVerificationToken(token);
        if (!result.equalsIgnoreCase(result)) {
            return "Invalid token";
        }
        Optional<UserMaser> user = userMasterService.getUserByPasswordresetToken(token);
        if (user.isPresent()) {
            userMasterService.changePassword(user.get(), passwordModel.getNewPass());
            return "Password reset Success";
        } else {
            return "Invalid token";
        }

    }

    @PostMapping("changepassword")
    public String changePassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        UserMaser user = userMasterService.findByEmail(passwordModel.getEmail());
        if (!userMasterService.checkPassword(user, passwordModel.getOldPass())) {
            return "Invalid old Password";
        } else {
            userMasterService.changePassword(user, passwordModel.getOldPass());
            return "Change Password Successfully";
        }
    }

    @GetMapping("/hello1")
    public String getMethodName() {
        return "Hii";
    }

    @GetMapping("api/hello2")
    public String getAPIMethodName() {
        return "Hii";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private void resendverificationToken(UserMaser user, String applicationUrl, String token) {
        String url = applicationUrl + "/verificationToken?token=" + token;
        log.info("Click link verify acc {}", url);
    }

    private String passwordresetTokenEmail(UserMaser user, String applicationUrl, String token) {
        String url = applicationUrl + "/savepassword?token=" + token;
        log.info("Click link to reset passwod {}", url);
        return url;
    }
}
