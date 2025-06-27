package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "designmastermain")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignMasterMain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long designMasterMainId;
	String designName;
    String width;
    String reed;
    int pick;
    String warp;
    String weft;
    double reedSpace;
    double weftWestage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quality_id", referencedColumnName = "qualityId")
    QualityMaster qualityMaster;
}
