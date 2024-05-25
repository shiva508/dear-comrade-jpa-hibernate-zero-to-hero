package com.comrade.config;

import com.comrade.entity.HotelType;
import jakarta.persistence.AttributeConverter;

public class HotelTypeConverter implements AttributeConverter<HotelType,String> {

    @Override
    public String convertToDatabaseColumn(HotelType hotelType) {
        if (hotelType == null){
            return null;
        }
        return hotelType.getCode();
    }

    @Override
    public HotelType convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        return HotelType.fromCode(dbData);
    }
}
