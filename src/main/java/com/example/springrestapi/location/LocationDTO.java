package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;

public record LocationDTO(Integer id, String name, Integer categoryId, String userId,
                          boolean isPrivate, java.util.Date created,
                          java.util.Date lastModified, String description,
                          double longitude, double latitude) {

    public static LocationDTO fromLocation(Location location) {
        double longitude = location.getCoordinates().getLongitude();
        double latitude = location.getCoordinates().getLatitude();
        return new LocationDTO(location.getId(),
                location.getName(),
                location.getCategoryId(),
                location.getUserId(),
                location.isPrivate(),
                location.getCreated(),
                location.getLastModified(),
                location.getDescription(),
                longitude,
                latitude
        );
    }
}