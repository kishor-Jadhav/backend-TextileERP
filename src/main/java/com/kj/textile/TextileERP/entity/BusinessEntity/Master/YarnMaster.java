package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

import com.kj.textile.TextileERP.entity.UserAuditEntity;
import com.kj.textile.TextileERP.enums.CountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "yarnmaster")
public class YarnMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long countId;
    String countName;
    double countNo;
    @Enumerated(EnumType.STRING)
    private CountType countType;

    @Embedded
    UserAuditEntity userAuditEntity;
}
