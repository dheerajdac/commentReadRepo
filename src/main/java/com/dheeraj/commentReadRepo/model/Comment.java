package com.dheeraj.commentReadRepo.model;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "comment")
public class Comment {

    @PrimaryKeyColumn(name = "id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private String id;

    @Column("text")
    private String text;

    @PrimaryKeyColumn(name = "p_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String parentId;

    @PrimaryKeyColumn(name = "o_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String orgId;

    @Column("cp_id")
    private String commentParentId;

    @Column("url")
    private String imageURL;

    @Column("img")
    private boolean isImage = false;

    @Column("del")
    private boolean isDeleted = false;

    @Column("c_by")
    private String createdBy;

    @PrimaryKeyColumn(name = "c_on", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    private LocalDateTime createdOn;

    @Column("edit")
    private boolean isEdited = false;

    @Column("l_m")
    private LocalDateTime lastModified;
    
}