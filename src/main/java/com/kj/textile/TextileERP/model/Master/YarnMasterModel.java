package com.kj.textile.TextileERP.model.Master;

import com.kj.textile.TextileERP.enums.CountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YarnMasterModel {
    long CountId;
    String countName;
    String countType;
    double countNo;

}
