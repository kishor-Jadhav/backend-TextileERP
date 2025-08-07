package com.kj.textile.TextileERP.entity.BaseEntity;

import com.kj.textile.TextileERP.entity.UserAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_menu_group_detail_master")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserMenuGroupDetailMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userMenuGroupDetailId;
    Long userMenuGroupId;

    boolean isAdd;
    boolean isEdit;
    boolean isView;
    boolean isDelete;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id",referencedColumnName = "menuId")
    UserMenuMaster userMenuMaster;
}
