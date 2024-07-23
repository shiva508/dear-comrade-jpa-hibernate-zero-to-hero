package com.comrade.repository;

import java.util.Optional;

import com.comrade.entity.GeoLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoLocationRepository extends JpaRepository<GeoLocationEntity, String> {
    public Optional<GeoLocationEntity> findByGeoLocation(String geoLocation);
}
