package com.comrade.entity;

import com.comrade.config.HotelTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;
import java.io.Serializable;

@Entity
@Table(name = "TBL_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderName;

    private Double price;

    private Double offer;

    @Formula(value = "(price * offer) * 100" )
    private Double totalPrice;

    @Enumerated(EnumType.ORDINAL)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Convert(converter = HotelTypeConverter.class)
    private HotelType hotelType;

}
