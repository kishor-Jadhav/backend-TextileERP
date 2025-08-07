package com.kj.textile.TextileERP.model.BaseModel;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.entity.UserAuditEntity;
import jakarta.persistence.Embedded;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class UserMenuMasterModel {
    Long menuId;
    String  menuName;
    Long parentMenuId;
    boolean isActive;
    int menuOrder;
    int menuLevel;
    String  menuRoute;
    boolean isNotDelete;
    AppClientProjectMaster clientProjectMaster;

}
