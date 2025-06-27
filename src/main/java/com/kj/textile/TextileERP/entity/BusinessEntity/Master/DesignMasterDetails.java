package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "designmasterdetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignMasterDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long designMasterDetailId;
    @Column(nullable = false)
    Long designMasterMainId;
    double pick;
    double actualWt;
    @Column(name = "`repeat`", nullable = false)
    private int repeat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Count_id", referencedColumnName = "CountId")
    YarnMaster yarnMaster;
}
