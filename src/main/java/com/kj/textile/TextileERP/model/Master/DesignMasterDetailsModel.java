package com.kj.textile.TextileERP.model.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.YarnMaster;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class DesignMasterDetailsModel {
    Long designMasterDetailId;
    Long designMasterMainId;
    double pick;
    double actualWt;
    int repeat;
    Long countId;
    YarnMaster countDetail;
}
