package com.comrade.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "PRESENTER_TBL")
public class PresenterEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRESENTER_ID")
    private Long presenterId;

    @Column(name = "PRESENTER_NAME")
    private String presenterName;
}
