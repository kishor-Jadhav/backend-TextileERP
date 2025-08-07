package com.kj.textile.TextileERP.model.BaseModel;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuGroupDetailMaster;
import com.kj.textile.TextileERP.entity.UserAuditEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class UserMenuGroupMasterModel {
    Long userMenuGroupId;
    String userGroupName;
    boolean isActiveMenuGroup;
    UserAuditEntity userAuditEntity;
    AppClientProjectMaster appClientProjectMaster;
    AppClientMaster appClientMaster;
    List<UserMenuGroupDetailMasterModel> userMenuGroupDetailMasterModel;
}
