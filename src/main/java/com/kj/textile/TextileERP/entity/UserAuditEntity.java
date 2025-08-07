package com.kj.textile.TextileERP.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class UserAuditEntity {
    private String createdBy;
    private LocalDateTime createdDate;

    private String updatedBy;
    private LocalDateTime updatedDate;

    Long auditEntryUserId;
    Long auditAccountYearId;
    Long auditClientId;
    Long auditProjectId;
}
