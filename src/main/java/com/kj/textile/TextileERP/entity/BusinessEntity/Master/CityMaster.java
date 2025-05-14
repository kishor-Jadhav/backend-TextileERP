package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "citymaster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long cityId;
    String cityName;
    String state;
    String pinCode;

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
}
