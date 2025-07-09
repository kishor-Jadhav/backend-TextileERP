package com.kj.textile.TextileERP.impl;

import com.kj.textile.TextileERP.AppDataInitializer.AppContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.entity.UserPasswordReset;
import com.kj.textile.TextileERP.entity.VerificationToken;
import com.kj.textile.TextileERP.model.UserMaserModel;
import com.kj.textile.TextileERP.repo.UserMaserRepo;
import com.kj.textile.TextileERP.repo.VerificationTokenRepo;
import com.kj.textile.TextileERP.services.UserMasterService;
import com.kj.textile.TextileERP.repo.UserPasswordResetRepo;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.kj.textile.TextileERP.AppDataInitializer.AppConstants.USERSETTING;

@Service
public class UserMaserImpl implements UserMasterService {

    @Autowired
    UserMaserRepo userMaserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    VerificationTokenRepo verificationTokenRepo;

    @Autowired
    UserPasswordResetRepo userPasswordResetRepo;

    @Autowired
    AppContext appContext;

    @Autowired
    UserContext userContext;

    @Override
    public UserMaser registerUser(UserMaserModel userMaserModel) {
        UserMaser userMaser = new UserMaser();
        userMaser.setUserName(userMaserModel.getUserName());
        userMaser.setPassword(passwordEncoder.encode(userMaserModel.getPassword()));
        userMaser.setEmail(userMaserModel.getEmail());
        userMaserRepo.save(userMaser);
        return userMaser;
    }

    @Override
    public void saveVerificationToken(String token, UserMaser userMaser) {
        VerificationToken verificationToken = new VerificationToken(userMaser,token);
        verificationTokenRepo.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        if(verificationToken == null){
            return "Invalid token";
        }
        UserMaser userMaser = verificationToken.getUserMaser();
        Calendar cal = Calendar.getInstance();
        if((verificationToken.getExpTime().getTime()- cal.getTime().getTime())<=0){
            verificationTokenRepo.delete(verificationToken);
            return "Token is expired";
        }
        userMaser.setEnabled(true);
        userMaserRepo.save(userMaser);
        return "User is activate";
    }

    @Override
    public VerificationToken getNewToken(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepo.save(verificationToken);
        return verificationToken;
    }

    @Override
    public UserMaser findByEmail(String email) {
        UserMaser user = userMaserRepo.findByEmail(email);
        return user;
    }

    

    @Override
    public void createNewTokenForResetPassword(UserMaser user, String token) {
        UserPasswordReset userPasswordReset = new UserPasswordReset(user,token);
        userPasswordResetRepo.save(userPasswordReset);
    }
    @Override
    public String validatePassResetVerificationToken(String token) {
        UserPasswordReset userPasswordReset = userPasswordResetRepo.findByToken(token);
        if(userPasswordReset == null){
            return "Invalid token";
        }
        UserMaser userMaser = userPasswordReset.getUserMaser();
        Calendar cal = Calendar.getInstance();
        if((userPasswordReset.getExpTime().getTime()- cal.getTime().getTime())<=0){
            userPasswordResetRepo.delete(userPasswordReset);
            return "Token is expired";
        }
        
        return "User is activate";
    }

    @Override
    public Optional<UserMaser> getUserByPasswordresetToken(String token) {
        return Optional.ofNullable(userPasswordResetRepo.findByToken(token).getUserMaser());
    }

    @Override
    public void changePassword(UserMaser userMaser, String newPass) {
        userMaser.setPassword(passwordEncoder.encode(newPass));
        userMaserRepo.save(userMaser);
    }

    @Override
    public boolean checkPassword(UserMaser user, String oldPass) {
        
    return passwordEncoder.matches(user.getPassword(), oldPass);
   }

    @Override
    public List<UserMaser> getAlluserList() {
        return userMaserRepo.findAll();
    }

    @Override
    public void setUserdetailsConfiguration(UserMaser userMaser) {
        appContext.setUserSetting(USERSETTING,"isTaskStart","true");
        if (userMaser.getLanguage()!= null){
            appContext.setUserSetting(USERSETTING,"language",userMaser.getLanguage());
        } else {
            appContext.setUserSetting(USERSETTING,"language","en");
        }

        appContext.setUserSetting(USERSETTING,"loginUser",userMaser.getUserName());


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMaser userMaster = userMaserRepo.findByAuthUserName(username);
        if (userMaster == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        UserDetails userDetails = User.withUsername(userMaster.getAuthUserName())
                .password(userMaster.getPassword())
                .authorities(userMaster.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName())) // Ensure roles are stored correctly
                        .collect(Collectors.toSet()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!userMaster.isEnabled())
                .build();

        // Print the UserDetails object
        System.out.println("UserDetails: " + userDetails);

        return userDetails;
    }
}
