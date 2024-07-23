package com.comrade.config.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonExceptonResponse {
    private String message;
    private Date occuredAt;
}
