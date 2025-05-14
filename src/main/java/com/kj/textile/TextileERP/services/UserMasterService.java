package com.kj.textile.TextileERP.services;

import java.util.List;
import java.util.Optional;

import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.entity.VerificationToken;
import com.kj.textile.TextileERP.model.UserMaserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserMasterService extends UserDetailsService {
    UserMaser registerUser(UserMaserModel userMaserModel);

    void saveVerificationToken(String token, UserMaser userMaser);

    String validateVerificationToken( String token);

    VerificationToken getNewToken(String token);

    UserMaser findByEmail(String email);

    void createNewTokenForResetPassword(UserMaser user, String token);

    String validatePassResetVerificationToken(String token);

    Optional<UserMaser> getUserByPasswordresetToken(String token);

    void changePassword(UserMaser userMaser, String newPass);

    boolean checkPassword(UserMaser user, String oldPass);
    List<UserMaser> getAlluserList();
}
