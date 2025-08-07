package com.kj.textile.TextileERP.model.BaseModel;

import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupMaster;
import com.kj.textile.TextileERP.entity.UserAuditEntity;
import com.kj.textile.TextileERP.entity.UserMaser;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class UserAssignGroupModel {
    Long userGroupAssignId;
    UserMaser userMaser ;
    UserMenuGroupMaster userMenuGroupMaster ;


}
