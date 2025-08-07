package com.kj.textile.TextileERP.ApplicationContext;

import lombok.*;

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

}
