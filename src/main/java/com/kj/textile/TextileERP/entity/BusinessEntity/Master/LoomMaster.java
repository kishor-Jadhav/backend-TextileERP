package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loommaster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoomMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long loomMasterId;
    String loomNo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shift_id", referencedColumnName = "shiftId")
    ShiftMaster shiftMaster;
    Long loomMasterDetailId;
    int unitId;


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

    public ShiftMaster getShiftMaster() {
        return shiftMaster;
    }

    public void setShiftMaster(ShiftMaster shiftMaster) {
        this.shiftMaster = shiftMaster;
    }
}
