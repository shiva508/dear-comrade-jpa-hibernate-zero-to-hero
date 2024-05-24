package com.comrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "TOPIC_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TopicDetailsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_DETAILS_ID")
    private Integer topicDetailsId;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "DETAILS")
    private String details;
}
