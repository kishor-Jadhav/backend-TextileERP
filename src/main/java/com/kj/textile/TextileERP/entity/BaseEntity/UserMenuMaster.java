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
@Table(name = "user_menu_master")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserMenuMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long menuId;
    String  menuName;
    Long parentMenuId;
    boolean isActive;
    int menuOrder;
    int menuLevel;
    boolean isNotDelete;
    String  menuRoute;
  // @ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "app_client_id", referencedColumnName = "appClientId")
   // AppClientMaster appClientMaster;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_client_project_id", referencedColumnName = "appClientProjectId")
    AppClientProjectMaster clientProjectMaster;

    @Embedded
    UserAuditEntity userAuditEntity;
}
