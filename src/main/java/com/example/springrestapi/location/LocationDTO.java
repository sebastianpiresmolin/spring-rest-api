package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;

public record LocationDTO(Integer id, String name, Integer categoryId, String symbol, boolean aPrivate,
                          java.util.Date created, java.util.Date lastModified, String description,
                          org.springframework.data.geo.Point coordinates) {

    public static LocationDTO fromLocation(Location location) {
        return new LocationDTO(
                location.getId(),
                location.getName(),
                location.getCategoryId(),
                location.getUserId(),
                location.isPrivate(),
                location.getCreated(),
                location.getLastModified(),
                location.getDescription(),
                location.getCoordinates()
                );
    }
}