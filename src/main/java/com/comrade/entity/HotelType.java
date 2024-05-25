package com.comrade.entity;

public enum HotelType {

    FIVE_STAR("*****"),
    FOUR_STAR("****"),
    THREE_STAR("***"),
    TWO_STAR("**"),
    ONE_STAR("*");

    private final String code;

    HotelType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static HotelType fromCode(String code) {
        return switch (code) {
            case "*****" -> HotelType.FIVE_STAR;
            case "****" -> HotelType.FOUR_STAR;
            case "***" -> HotelType.THREE_STAR;
            case "**" -> HotelType.TWO_STAR;
            default -> HotelType.ONE_STAR;
        };
    }
}
