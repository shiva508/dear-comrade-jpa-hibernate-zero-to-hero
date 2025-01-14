package com.comrade.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class OpinionModel implements Serializable {

    private Long opinionId;

    private String opinionTitle;

    private String opinionDesc;

    private String leaderName;

    private Long newsId;
}
