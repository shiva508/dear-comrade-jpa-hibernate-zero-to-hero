package com.comrade.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@Builder
public class Location implements Serializable {

    private String village;

    private String street;
}
