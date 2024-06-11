package com.comrade.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
@Table(name = "TBL_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DynamicUpdate
@AttributeOverrides({@AttributeOverride(name = "originLocation.village",column = @Column(name = "ORG_VILLAGE")),
                     @AttributeOverride(name = "originLocation.street", column = @Column(name = "ORG_STREET")),
                     @AttributeOverride(name = "destinationLocation.village",column = @Column(name = "DEST_VILLAGE")),
                     @AttributeOverride(name = "destinationLocation.street",column = @Column(name = "DEST_STREET"))})
public class Order implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderName;

    private Double price;

    private Double offer;

    private Location originLocation;

    private Location destinationLocation;

    @Override
    public Long getId() {
        return orderId;
    }

    @Override
    public boolean isNew() {
        return orderId == null ? true: false;
    }
}
