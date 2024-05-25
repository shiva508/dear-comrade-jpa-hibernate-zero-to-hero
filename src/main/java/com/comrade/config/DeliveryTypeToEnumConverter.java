package com.comrade.config;

import com.comrade.entity.DeliveryType;
import com.comrade.entity.OrderType;
import org.springframework.core.convert.converter.Converter;

public class DeliveryTypeToEnumConverter implements Converter<String, DeliveryType> {

    @Override
    public DeliveryType convert(String source) {
        return DeliveryType.valueOf(source);
    }
}
