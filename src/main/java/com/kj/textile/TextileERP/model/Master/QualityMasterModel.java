package com.kj.textile.TextileERP.model.Master;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QualityMasterModel {
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
