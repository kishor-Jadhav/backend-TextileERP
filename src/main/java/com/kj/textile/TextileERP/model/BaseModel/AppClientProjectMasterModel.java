package com.kj.textile.TextileERP.model.BaseModel;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class AppClientProjectMasterModel {
    Long appClientProjectId;

    String projectName;
    String projectCode;
    String projectAlias;


}
