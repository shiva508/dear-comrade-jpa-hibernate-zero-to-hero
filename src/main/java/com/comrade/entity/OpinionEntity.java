package com.comrade.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "OPINION_TBL")
@Data
public class OpinionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OPINION_ID")
    private Long opinionId;

    @Column(name = "OPINION_TITLE")
    private String opinionTitle;

    @Column(name = "OPINION_DESC")
    private String opinionDesc;

    @Column(name = "LEADER_NAME")
    private String leaderName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "NEWS_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public NewsEntity newsEntity;

}
