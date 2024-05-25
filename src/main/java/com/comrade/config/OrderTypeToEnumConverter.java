package com.comrade.config;

import com.comrade.entity.EventType;
import com.comrade.entity.OrderType;
import org.springframework.core.convert.converter.Converter;

public class OrderTypeToEnumConverter implements Converter<String, OrderType> {

    @Override
    public OrderType convert(String source) {
        return OrderType.valueOf(source);
    }
}
