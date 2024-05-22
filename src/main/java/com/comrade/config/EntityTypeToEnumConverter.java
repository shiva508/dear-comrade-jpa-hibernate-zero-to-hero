package com.comrade.config;

import com.comrade.entity.EventType;
import org.springframework.core.convert.converter.Converter;

public class EntityTypeToEnumConverter implements Converter<String, EventType> {

    @Override
    public EventType convert(String source) {
        return EventType.valueOf(source);
    }
}
