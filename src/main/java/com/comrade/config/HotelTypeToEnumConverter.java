package com.comrade.config;

import com.comrade.entity.HotelType;
import com.comrade.entity.OrderType;
import org.springframework.core.convert.converter.Converter;

public class HotelTypeToEnumConverter implements Converter<String, HotelType> {

    @Override
    public HotelType convert(String source) {
        return HotelType.valueOf(source);
    }
}
