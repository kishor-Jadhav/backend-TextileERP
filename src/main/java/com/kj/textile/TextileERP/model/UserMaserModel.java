package com.kj.textile.TextileERP.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data 
public class UserMaserModel {
     Long userId;
     String userName;
     String password;
     String email;
     String role;
     String userRole;
     boolean isActive;
     boolean isValidateUser;
     String authToken;
  
}
