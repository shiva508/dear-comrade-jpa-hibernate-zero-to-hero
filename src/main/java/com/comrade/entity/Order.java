package com.comrade.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "TBL_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@AttributeOverrides({@AttributeOverride(name = "originLocation.village",column = @Column(name = "ORG_VILLAGE")),
                     @AttributeOverride(name = "originLocation.street", column = @Column(name = "ORG_STREET")),
                     @AttributeOverride(name = "destinationLocation.village",column = @Column(name = "DEST_VILLAGE")),
                     @AttributeOverride(name = "destinationLocation.street",column = @Column(name = "DEST_STREET"))})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderName;

    private Double price;

    private Double offer;

    private Location originLocation;

    private Location destinationLocation;
}
