package com.comrade.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne(mappedBy = "topicEntity",
              cascade = CascadeType.ALL,
              fetch = FetchType.EAGER,
              orphanRemoval = true)
    private TopicDetailsEntity topicDetailsEntity;

    public void addDetails(TopicDetailsEntity newTopicDetailsEntity){
        newTopicDetailsEntity.setTopicEntity(this);
        this.setTopicDetailsEntity(newTopicDetailsEntity);
    }

    public void removeDetails(){
            if (this.topicDetailsEntity != null){
                this.topicDetailsEntity.setTopicEntity(null);
                this.topicDetailsEntity = null;
            }
    }

}
