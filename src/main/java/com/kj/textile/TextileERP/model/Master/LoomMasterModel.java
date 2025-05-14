package com.kj.textile.TextileERP.model.Master;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class LoomMasterModel {
    Long loomMasterId;
    String loomNo;
    Long shiftId;
    String shiftName;
    int unitId;
    Long loomMasterDetailId;

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public Long getLoomMasterDetailId() {
        return loomMasterDetailId;
    }

    public void setLoomMasterDetailId(Long loomMasterDetailId) {
        this.loomMasterDetailId = loomMasterDetailId;
    }

    public Long getLoomMasterId() {
        return loomMasterId;
    }

    public void setLoomMasterId(Long loomMasterId) {
        this.loomMasterId = loomMasterId;
    }

    public String getLoomNo() {
        return loomNo;
    }

    public void setLoomNo(String loomNo) {
        this.loomNo = loomNo;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }
}
