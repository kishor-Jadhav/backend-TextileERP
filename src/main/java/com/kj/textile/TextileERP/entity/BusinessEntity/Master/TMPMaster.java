package com.kj.textile.TextileERP.entity.BusinessEntity.Master;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Entity
@Table(name = "tmp_data")
public class TMPMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long tmpId;
    String data1;
    String createdName;
    Date createdTime;
}
