package com.comrade.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "TBL_PARAGRAPH")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Paragraph implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAGRAPH_ID")
    private Long paragraphId;
    private String paragraphHeading;
    private String paragraph;
    private String codeSnippet;
}
