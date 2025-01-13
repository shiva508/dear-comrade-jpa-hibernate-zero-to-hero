package com.comrade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class CommonResponse {

    private String message;

    private Integer statusCode;

    private Date timestamp;
}
