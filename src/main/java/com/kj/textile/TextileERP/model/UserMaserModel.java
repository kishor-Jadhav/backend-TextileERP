package com.kj.textile.TextileERP.model;


import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserAssignGroupEntity;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.entity.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class UserMaserModel {
     Long userId;
     String userName;
     String authUserName;
     String password;
     String email;

     String userRole;
     boolean isActive;
     boolean isValidateUser;
     String authToken;
     boolean generateNewPassword;
     String userAdminKeys;
     String accountName;
     String language;
     AppClientMaster appClientMaster;
     AppClientProjectMaster appClientProjectMaster;
     boolean enabled;
     boolean isDactive;
     Set<UserRoles> userRoles = new HashSet<>()  ;
     UserAssignGroupEntity userAssignGroupEntity;
     List<UserMenuGroupDetailMaster> UserMenuGroupDetailMaster;
}
