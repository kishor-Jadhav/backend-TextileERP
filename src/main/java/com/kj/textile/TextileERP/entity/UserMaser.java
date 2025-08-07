package com.kj.textile.TextileERP.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "usermaster")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserMaser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    String userName;
    String authUserName;
    @Column(length = 60)
    String password;
    String email;
    String language;
    String userAdminKeys;
    String accountName;
    Long appClientId;
    Long appClientProjectId;
    boolean enabled;
    boolean isDactive;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_access_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude // Prevents circular reference iss
    @EqualsAndHashCode.Exclude
    Set<UserRoles> roles = new HashSet<>()  ;

    public boolean isDactive() {
        return isDactive;
    }

    public void setActive(boolean active) {
        isDactive = active;
    }




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



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getAuthUserName() {
        return authUserName;
    }

    public void setAuthUserName(String authUserName) {
        this.authUserName = authUserName;
    }
}
