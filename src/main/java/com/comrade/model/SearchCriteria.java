package com.comrade.model;

import com.comrade.util.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class SearchCriteria implements Serializable {

    private String key;

    private Object value;

    private OperationType operation;

    private boolean childOperation;

    private Timestamp from;

    private Timestamp to;

}
