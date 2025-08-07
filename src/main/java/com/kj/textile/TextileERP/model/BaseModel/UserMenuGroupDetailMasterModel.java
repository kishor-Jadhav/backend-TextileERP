package com.kj.textile.TextileERP.model.BaseModel;

import com.kj.textile.TextileERP.entity.BaseEntity.UserMenuMaster;
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
public class UserMenuGroupDetailMasterModel {
    Long userMenuGroupDetailId;
    Long userMenuGroupId;

    boolean isAdd;
    boolean isEdit;
    boolean isView;
    boolean isDelete;

    UserMenuMaster userMenuMaster;
}
