package com.kj.textile.TextileERP.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppClientProjectMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long appClientProjectId;

    String projectName;
    String projectCode;
    String projectAlias;

}
