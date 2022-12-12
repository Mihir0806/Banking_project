package com.banking.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class CommonDataModel {

    public enum activeStatus{
        ACTIVE,INACTIVE,PENDING
    }

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @CreationTimestamp
    private Date creationTime;

    @UpdateTimestamp
    private Date lastModifiedTime;

    @Enumerated(EnumType.STRING)
    private activeStatus activeStatus;

    @Version
    private int version;

}
