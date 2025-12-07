package com.kj.textile.TextileERP.ApplicationContext;

import com.kj.textile.TextileERP.entity.UserRoles;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContextDTO {
    private String username;
    private String ip;
    private String userType;
    Long auditEntryUserId;
    Long auditAccountYearId;
    Long auditClientId;
    Long auditProjectId;
    Set<UserRoles> roles = new HashSet<>()  ;

}
