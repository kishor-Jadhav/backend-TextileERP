package com.kj.textile.TextileERP.entity.BaseEntity;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.entity.UserAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_menu_group_master")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserMenuGroupMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userMenuGroupId;
    String userGroupName;
    boolean isActiveMenuGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_client_id", referencedColumnName = "appClientId")
    AppClientMaster appClientMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_client_project_id", referencedColumnName = "appClientProjectId")
    AppClientProjectMaster appClientProjectMaster;
    @Embedded
    UserAuditEntity userAuditEntity;
}
