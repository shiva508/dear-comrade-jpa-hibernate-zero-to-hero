package com.comrade.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "NEWS_TBL")
@Data
public class NewsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NEWS_ID")
    private Long newsId;

    @Column(name = "NEWS_TITLE")
    private String newsTitle;

    @Column(name = "NEWS_AUTHOR")
    private String newsAuthor;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "MODIFIED_AT")
    private Timestamp modifiedAt;

}
