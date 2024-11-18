package com.example.springrestapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public GeolatteGeomModule geolatteGeomModule() {
        return new GeolatteGeomModule();
    }
}
