package com.kj.textile.TextileERP.model.BaseModel;

import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AppClientMasterModel {
    Long appClientId;
    String clientUniqueId;
    String clientName;
    String clientAlias;

    AppClientProjectMaster appClientProjectMaster;
}
