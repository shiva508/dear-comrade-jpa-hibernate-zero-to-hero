package com.comrade.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
@Table(name = "TOPIC_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicDetailsEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_DETAILS_ID")
    private Long topicDetailsId;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "DETAILS")
    private String details;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonBackReference
    private TopicEntity topicEntity;

    @Override
    public Long getId() {
        return topicDetailsId;
    }

    @Override
    public boolean isNew() {
        return topicDetailsId == null ? true: false;
    }
}
