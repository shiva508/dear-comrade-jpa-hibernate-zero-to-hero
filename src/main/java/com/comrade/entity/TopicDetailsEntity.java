package com.comrade.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "TOPIC_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicDetailsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_DETAILS_ID")
    private Integer topicDetailsId;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "DETAILS")
    private String details;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOPIC_ID")
    @JsonBackReference
    private TopicEntity topicEntity;
}
