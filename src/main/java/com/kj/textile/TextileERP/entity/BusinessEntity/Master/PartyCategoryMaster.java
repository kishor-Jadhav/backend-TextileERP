package com.kj.textile.TextileERP.entity.BusinessEntity.Master;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "partycategorymaster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartyCategoryMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long partyCategoryId;
    String partyCategoryName;

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
}

