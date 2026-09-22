package com.Ts.Employee_Management.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AbstractPersistable implements Serializable {

    @Id
    @Column(nullable = false)
    private String id;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private String updatedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;

    private Boolean isActive;

    private Boolean isDeleted;


}
