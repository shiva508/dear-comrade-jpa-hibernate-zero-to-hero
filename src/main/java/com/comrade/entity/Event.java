package com.comrade.entity;

import com.comrade.entity.basictype.EventTypeDef;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import java.io.Serializable;

@Entity
@Table(name = "EVENT")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    private String createdDate;

    @Type(EventTypeDef.class)
    @Column(name = "event_type")
    private EventType eventType;

}
