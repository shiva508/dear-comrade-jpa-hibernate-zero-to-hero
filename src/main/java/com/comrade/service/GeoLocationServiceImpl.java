package com.comrade.service;

import java.util.List;

import com.comrade.config.exception.ResponseNotFound;
import com.comrade.entity.GeoLocationEntity;
import com.comrade.repository.GeoLocationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    private final GeoLocationRepository geoLocationRepository;

    public GeoLocationServiceImpl(GeoLocationRepository geoLocationRepository) {
        this.geoLocationRepository = geoLocationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "geos")
    public List<GeoLocationEntity> getGeoLocationEntities() {
        return geoLocationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "geos", key = "#geolocation")
    public GeoLocationEntity getGeoLocation(String geolocation) {
        return geoLocationRepository.findByGeoLocation(geolocation)
                .orElseThrow(() -> new ResponseNotFound("Geo Location Not Found"));
    }

}
