package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

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
@Table(name = "qualitymaster")
public class QualityMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long qualityId;
    String width;
    String reed;
    int pick;
    String warp;
    String weft;
    double reedSpace;
    String qualityName;
    String qualityAlice;
}
