package com.kj.textile.TextileERP.model.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.QualityMaster;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class DesignMasterMainModel {
    Long designMasterMainId;
    String designName;
    String width;
    String reed;
    int pick;
    String warp;
    String weft;
    double reedSpace;
    double weftWestage;
    QualityMaster qualMaster;

    List<DesignMasterDetailsModel> designMasterDetailsModel;
}
