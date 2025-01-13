package com.comrade.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NEWS_TBL")
@Data
@NamedEntityGraph(name = "NewsEntity.opinions",attributeNodes = @NamedAttributeNode("opinions"))
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

    @OneToMany(mappedBy = "newsEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OpinionEntity> opinions = new ArrayList<>();

    public void addOpinion(OpinionEntity opinionEntity){
        opinions.add(opinionEntity);
        opinionEntity.setNewsEntity(this);
    }

}
