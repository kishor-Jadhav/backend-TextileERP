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

     public Long getUserId() {
          return userId;
     }

     public void setUserId(Long userId) {
          this.userId = userId;
     }

     public String getUserName() {
          return userName;
     }

     public void setUserName(String userName) {
          this.userName = userName;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getRole() {
          return role;
     }

     public void setRole(String role) {
          this.role = role;
     }

     public String getUserRole() {
          return userRole;
     }

     public void setUserRole(String userRole) {
          this.userRole = userRole;
     }

     public boolean isActive() {
          return isActive;
     }

     public void setActive(boolean active) {
          isActive = active;
     }

     public boolean isValidateUser() {
          return isValidateUser;
     }

     public void setValidateUser(boolean validateUser) {
          isValidateUser = validateUser;
     }

     public String getAuthToken() {
          return authToken;
     }

     public void setAuthToken(String authToken) {
          this.authToken = authToken;
     }
}
