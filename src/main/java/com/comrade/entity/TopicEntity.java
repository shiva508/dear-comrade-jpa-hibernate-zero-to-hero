package com.comrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "TOPIC")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_ID")
    private Long topicId;

    @Column(name = "TOPIC_NAME")
    private String topicName;

    /*
    This type of mapping is used when we need to persist parent and child, there is no pre-saved data involved
     */

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "TOPIC_DETAILS_ID", referencedColumnName = "TOPIC_DETAILS_ID")
    private TopicDetailsEntity topicDetailsEntity;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private TopicTypeEntity topicTypeEntity;

}
