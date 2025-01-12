package com.comrade.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchModel implements Serializable {

    private String newsTitle;

    private String childOpinionDesc;

    private Integer page = 0;

    private Integer size = 10;

}
