package com.comrade.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class PresenterModel implements Serializable {

    private Long presenterId;

    private String presenterName;
}
