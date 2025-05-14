package com.kj.textile.TextileERP.model.Master;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class PartyMasterModel {
    Long partyId;
    String partAddress;
    String partyName;

    String panNo;

    String state;
    String pinCode;
    String mobNo;
    String phNo;
    String emailId;
    Long brokerId;
    Long cityId;
    String cityName;
    String gstNo;

    Long partyCategoryId;
    String partyCategoryName;


    String brokerName;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public Long getPartyCategoryId() {
        return partyCategoryId;
    }

    public void setPartyCategoryId(Long partyCategoryId) {
        this.partyCategoryId = partyCategoryId;
    }

    public String getPartyCategoryName() {
        return partyCategoryName;
    }

    public void setPartyCategoryName(String partyCategoryName) {
        this.partyCategoryName = partyCategoryName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}
