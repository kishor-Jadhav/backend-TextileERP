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
public class AppClientMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long appClientId;
    String clientUniqueId;
    String clientName;
    String clientAlias;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_client_project_id", referencedColumnName = "appClientProjectId")
    AppClientProjectMaster appClientProjectMaster;
}
