package com.comrade.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "PARTY_TBL")
@Data
public class PartyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PARTY_ID")
    private Long partyId;

    @Column(name = "PARTY_NAME")
    private String partyName;

    @Column(name = "PARTY_PRESIDENT")
    private String partyPresident;



}
