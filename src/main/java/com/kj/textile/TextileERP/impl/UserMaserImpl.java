package com.kj.textile.TextileERP.impl;

import com.kj.textile.TextileERP.AppDataInitializer.AppContext;
import com.kj.textile.TextileERP.AppUtils.CommonUtils;
import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.Exceptions.ApplicationDataException;
import com.kj.textile.TextileERP.entity.*;
import com.kj.textile.TextileERP.entity.BaseEntity.UserAssignGroupEntity;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.enums.UserRolesEnum;
import com.kj.textile.TextileERP.model.BaseModel.UserMenuMasterModel;
import com.kj.textile.TextileERP.model.UserMaserModel;
import com.kj.textile.TextileERP.repo.BaseRepo.UserAssignGroupRepo;
import com.kj.textile.TextileERP.repo.UserMaserRepo;
import com.kj.textile.TextileERP.repo.UserRoleRepository;
import com.kj.textile.TextileERP.repo.VerificationTokenRepo;
import com.kj.textile.TextileERP.services.BaseService.AppClientMasterService;
import com.kj.textile.TextileERP.services.BaseService.AppClientProjectMasterService;
import com.kj.textile.TextileERP.services.BaseService.UserAssignGroupService;
import com.kj.textile.TextileERP.services.BaseService.UserMenuGroupDetailMasterService;
import com.kj.textile.TextileERP.services.UserMasterService;
import com.kj.textile.TextileERP.repo.UserPasswordResetRepo;

import java.util.*;
import java.util.stream.Collectors;

import io.jsonwebtoken.MalformedJwtException;
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

     @Autowired
    AppClientProjectMasterService appClientProjectMasterService;

     @Autowired
     AppClientMasterService appClientMasterService;

     @Autowired
     UserRoleRepository userRoleRepository;

     @Autowired
     UserAssignGroupService userAssignGroupService;

     @Autowired
     UserMenuGroupDetailMasterService userMenuGroupDetailMasterService;


    @Override
    public UserMaser registerUserByAdmin(UserMaserModel userMaserModel) {
        // 1. Fetch the Role Entity (already done correctly)
        UserRoles userRoles = userRoleRepository.findByRoleName(userMaserModel.getUserRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // 2. Prepare the User Entity
        UserMaser userMaser = new UserMaser();
        userMaser.setUserName(userMaserModel.getUserName());
        userMaser.setPassword(passwordEncoder.encode(userMaserModel.getPassword()));
        userMaser.setEmail(userMaserModel.getEmail());
        userMaser.setAuthUserName(userMaserModel.getAuthUserName());
        userMaser.setAccountName(userMaserModel.getAccountName());
        userMaser.setEnabled(true);  // explicitly enabling user (your choice)
        userMaser.setDactive(false); // assuming user is active
        userMaser.setAppClientId(userMaserModel.getAppClientMaster().getAppClientId());
        userMaser.setAppClientProjectId(userMaserModel.getAppClientProjectMaster().getAppClientProjectId());



        // 4. Save User with Roles
         userMaserRepo.save(userMaser);

        return userMaser;
    }

    @Override
    public UserMaser updateRegisterUserByAdmin(UserMaserModel userMaserModel) {
        UserMaser userMaser = userMaserRepo.findByUserId(userMaserModel.getUserId());
        UserRoles userRoles = userRoleRepository.findByRoleName(userMaserModel.getUserRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        userMaser.setUserName(userMaserModel.getUserName());
        if(userMaserModel.isGenerateNewPassword()){
            userMaser.setPassword(passwordEncoder.encode(userMaserModel.getPassword()));
        }
        userMaser.setEnabled(userMaserModel.isEnabled());
        userMaser.setDactive(userMaserModel.isDactive());
        userMaser.setEmail(userMaserModel.getEmail());
        // 3. Set the Role Properly
        userMaser.getRoles().add(userRoles);
        userMaser.setAccountName(userMaserModel.getAccountName());
        userMaser.setAppClientId(userMaserModel.getAppClientMaster().getAppClientId());
        userMaser.setAppClientProjectId(userMaserModel.getAppClientProjectMaster().getAppClientProjectId());
        userMaserRepo.save(userMaser);

        return userMaser;
    }

    @Override
    public List<UserMaserModel> getUserCreationList() {
        CommonUtils utils = new CommonUtils();
        // Take Login User priority
//        UserContextDTO userContext = UserContext.get();
//        Optional<Integer> userRolePriorityOption = userContext.getRoles().stream().map(item->item.getRolePriority())
//                .findFirst();
//        int loginUserPriority =0;
//        if (userRolePriorityOption.isPresent()) {
//            loginUserPriority = userRolePriorityOption.get();
//
//        }
        int loginUserPriority =  utils.getLoginUserPriority();

        // Check User Has SuperId role
//        List<String> rolesToContains = List.of("ROLE_SUPERADMIN");
//        boolean has_ROLE_SUPERADMIN =  userContext.getRoles().stream()
//                .map(UserRoles::getRoleName)
//                .anyMatch(rolesToContains::contains);
        boolean has_ROLE_SUPERADMIN = utils.isUserSuperAdmin();

        List<UserMaser> userMaser = userMaserRepo.findAll();
        List<UserMaserModel> userMaserModel = new ArrayList<>();
        for(UserMaser data: userMaser){
            boolean hasNoneOfTheExcludedRoles = false;

            // Get Record User Priority
            Optional<Integer> rolePriorityOption = data.getRoles().stream().map(item->item.getRolePriority())
                    .findFirst();
            int priority =0;
            if (rolePriorityOption.isPresent()) {
                priority = rolePriorityOption.get();

            }


            // Check if NONE of the roles in the user's set match any of the excluded names
//            boolean hasNoneOfTheExcludedRoles =  data.getRoles().stream()
//                    .map(UserRoles::getRoleName)        // Get a Stream<String> of just the rolenames
//                    .noneMatch(rolesToExclude::contains);


           if(has_ROLE_SUPERADMIN){
               hasNoneOfTheExcludedRoles = true;
           } else if (loginUserPriority<priority){
               hasNoneOfTheExcludedRoles = true;
           }


            if (hasNoneOfTheExcludedRoles) {
                // This block executes IF the user does NOT have ROLE_SUPERADMIN AND NOT ROLE_ADMIN


                UserMaserModel model = new UserMaserModel();
                model.setUserId(data.getUserId());
                model.setUserName(data.getUserName());
                model.setAuthUserName(data.getAuthUserName());
                model.setLanguage(data.getLanguage());
                //  model.setUserAdminKeys(data.getUserAdminKeys());
                model.setEmail(data.getEmail());
                model.setDactive(data.isDactive());
                model.setEmail(data.getEmail());
                model.setUserId(data.getUserId());
                model.setUserRoles(data.getRoles());
                model.setAccountName(data.getAccountName());
                AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(data.getAppClientProjectId());
                AppClientMaster appClientMaster = appClientMasterService.getDataById(data.getAppClientId());
                model.setAppClientProjectMaster(appClientProjectMaster);
                model.setAppClientMaster(appClientMaster);
                userMaserModel.add(model);
            }
        }
        return userMaserModel;
    }
    @Override
    public List<UserMaserModel> getUserList() {
        List<UserMaser> userMaser = userMaserRepo.findAll();
        List<UserMaserModel> userMaserModel = new ArrayList<>();
        for(UserMaser data: userMaser){
            UserMaserModel model = new UserMaserModel();
            model.setUserId(data.getUserId());
            model.setUserName(data.getUserName());
            model.setAuthUserName(data.getAuthUserName());
            model.setLanguage(data.getLanguage());
          //  model.setUserAdminKeys(data.getUserAdminKeys());
            model.setEmail(data.getEmail());
            model.setDactive(data.isDactive());
            model.setEmail(data.getEmail());
            model.setUserId(data.getUserId());
            model.setUserRoles(data.getRoles());
            model.setAccountName(data.getAccountName());
            AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(data.getAppClientProjectId());
            AppClientMaster appClientMaster = appClientMasterService.getDataById(data.getAppClientId());
            model.setAppClientProjectMaster(appClientProjectMaster);
            model.setAppClientMaster(appClientMaster);
            userMaserModel.add(model);
        }
        return userMaserModel;
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
    public UserMaserModel findUserById(Long Id) {
        UserMaser data = userMaserRepo.findByUserId(Id);


        UserMaserModel model = new UserMaserModel();
        model.setUserId(data.getUserId());
        model.setUserName(data.getUserName());
        model.setAuthUserName(data.getAuthUserName());
        model.setLanguage(data.getLanguage());
        model.setUserAdminKeys(data.getUserAdminKeys());
        model.setEmail(data.getEmail());
        model.setDactive(data.isDactive());
        model.setEmail(data.getEmail());
        model.setUserRoles(data.getRoles());
        model.setAccountName(data.getAccountName());
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(data.getAppClientProjectId());
        AppClientMaster appClientMaster = appClientMasterService.getDataById(data.getAppClientId());
        model.setAppClientProjectMaster(appClientProjectMaster);
        model.setAppClientMaster(appClientMaster);


        return model;
    }

    @Override
    public UserMaserModel getUserConfigData(Long Id) {
        UserMaser data = userMaserRepo.findByUserId(Id);


        UserMaserModel model = new UserMaserModel();
        model.setUserId(data.getUserId());
        model.setUserName(data.getUserName());
        model.setAuthUserName(data.getAuthUserName());
        model.setLanguage(data.getLanguage());
        model.setUserAdminKeys(data.getUserAdminKeys());
        model.setEmail(data.getEmail());
        model.setDactive(data.isDactive());
        model.setEmail(data.getEmail());
        model.setUserRoles(data.getRoles());
        model.setAccountName(data.getAccountName());
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterService.getDataById(data.getAppClientProjectId());
        AppClientMaster appClientMaster = appClientMasterService.getDataById(data.getAppClientId());
        model.setAppClientProjectMaster(appClientProjectMaster);
        model.setAppClientMaster(appClientMaster);
        UserAssignGroupEntity userAssignGroupEntity =  userAssignGroupService.findByUserMaser(Id);
        model.setUserAssignGroupEntity(userAssignGroupEntity);
        List<UserMenuGroupDetailMaster> UserMenuGroupDetailMaster = userMenuGroupDetailMasterService.getDetailList(userAssignGroupEntity.getUserMenuGroupMaster().getUserMenuGroupId());
        Optional<String> roleNameOptional = data.getRoles().stream().map(item->item.getRoleName())
                .findFirst();
        String userRole ="";
        if (roleNameOptional.isPresent()) {
            userRole = roleNameOptional.get();

        }
        List<UserMenuMasterModel>  userMenuMasterModel = userMenuGroupDetailMasterService.getMenuDetailList(userAssignGroupEntity.getUserMenuGroupMaster().getUserMenuGroupId(),userRole);
        model.setUserMenuGroupDetailMaster(userMenuMasterModel);
        return model;
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
    public void setUserContexDTOData(UserMaser userMaster){
        try {
        // Context Data
        UserContextDTO userContextDTO = new UserContextDTO();
        userContextDTO.setUsername(userMaster.getAuthUserName());
        userContextDTO.setRoles(userMaster.getRoles());
        userContextDTO.setAuditEntryUserId(userMaster.getUserId());
        userContextDTO.setAuditAccountYearId(3L);
        userContextDTO.setAuditClientId(userMaster.getAppClientId());
        userContextDTO.setAuditProjectId(userMaster.getAppClientProjectId());
        UserContext.set(userContextDTO);
        //
        } catch (MalformedJwtException e) {
            throw new ApplicationDataException("User context Not set");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMaser userMaster = userMaserRepo.findByAuthUserName(username);

        if (userMaster == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        if (userMaster.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("Role not define for User: " + username);
        }
        setUserContexDTOData(userMaster);

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
