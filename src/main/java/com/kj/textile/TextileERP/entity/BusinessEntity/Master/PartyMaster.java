package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partymaster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartyMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long partyId;
    String partAddress;
    String partyName;

    String panNo;


    String mobNo;
    String phNo;
    String emailId;
    Long brokerId;

    String gstNo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "party_category_id", referencedColumnName = "partyCategoryId")
    PartyCategoryMaster partyCategoryMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "cityId")
    CityMaster cityMaster;

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getPartAddress() {
        return partAddress;
    }

    public void setPartAddress(String partAddress) {
        this.partAddress = partAddress;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }



    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public PartyCategoryMaster getPartyCategoryMaster() {
        return partyCategoryMaster;
    }

    public void setPartyCategoryMaster(PartyCategoryMaster partyCategoryMaster) {
        this.partyCategoryMaster = partyCategoryMaster;
    }

    public CityMaster getCityMaster() {
        return cityMaster;
    }

    public void setCityMaster(CityMaster cityMaster) {
        this.cityMaster = cityMaster;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}
