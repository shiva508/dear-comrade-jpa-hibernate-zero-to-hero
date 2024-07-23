package com.comrade.service;

import com.comrade.entity.GeoLocationEntity;

import java.util.List;

public interface GeoLocationService {
    public List<GeoLocationEntity> getGeoLocationEntities();

    public GeoLocationEntity getGeoLocation(String geolocation);
}
