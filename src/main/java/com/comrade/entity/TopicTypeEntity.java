package com.comrade.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TOPIC_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TopicTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_TYPE_ID")
    private Integer topicTypeId;

    @Column(name = "TOPIC_TYPE_NAME")
    private String topicTypeName;

}
