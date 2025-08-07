package com.kj.textile.TextileERP.entity.BaseEntity;

import com.kj.textile.TextileERP.entity.UserAuditEntity;
import com.kj.textile.TextileERP.entity.UserMaser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_group_assign")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAssignGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userGroupAssignId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    UserMaser userMaser ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_menu_group_id", referencedColumnName = "userMenuGroupId")
    UserMenuGroupMaster userMenuGroupMaster ;

    @Embedded
    UserAuditEntity userAuditEntity;
}
