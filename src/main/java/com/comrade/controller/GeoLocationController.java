package com.comrade.controller;

import java.util.List;
import java.util.Optional;

import com.comrade.entity.GeoLocationEntity;
import com.comrade.service.GeoLocationService;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoLocationController {

    private final GeoLocationService geoLocationService;

    private final CacheManager cacheManager;

    public GeoLocationController(GeoLocationService geoLocationService, CacheManager cacheManager) {
        this.geoLocationService = geoLocationService;
        this.cacheManager = cacheManager;
    }

    @GetMapping("/geos")
    public ResponseEntity<?> getAllGeoLocations() {
        List<GeoLocationEntity> geoLocationEntities = geoLocationService.getGeoLocationEntities();

        return new ResponseEntity<>(geoLocationEntities, HttpStatus.OK);
    }

    @GetMapping("/geo/{geolocation}")
    public ResponseEntity<?> getGeoLocation(@PathVariable("geolocation") String geolocation) {
        GeoLocationEntity geoLocationEntitity = geoLocationService.getGeoLocation(geolocation);

        return new ResponseEntity<>(geoLocationEntitity, HttpStatus.OK);
    }

    @DeleteMapping("/clearcache/{cachedkeyname}")
    public void clearCache(@PathVariable("cachedkeyname") String cachedkeyname) {
        Optional<String> nameopt = cacheManager.getCacheNames().stream().filter(name -> name.equals(cachedkeyname))
                .findFirst();
        if (nameopt.isPresent()) {
            cacheManager.getCache(nameopt.get()).clear();
        }
    }

}
