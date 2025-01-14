package com.comrade.model;

import com.comrade.util.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class SearchCriteria {

    private String key;

    private Object value;

    private OperationType operation;

    private boolean childOperation;

    private Timestamp from;

    private Timestamp to;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public boolean isChildOperation() {
        return childOperation;
    }

    public void setChildOperation(boolean childOperation) {
        this.childOperation = childOperation;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }
}
