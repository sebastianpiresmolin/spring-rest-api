package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

public record LocationDTO(Integer id, String name, Integer categoryId, String userId,
                          boolean isPrivate, java.util.Date created,
                          java.util.Date lastModified, String description, float latitude, float longitude,
                          boolean isDeleted) {

    public static LocationDTO fromLocation(Location location) {
        G2D position = location.getCoordinate().getPosition();
        return new LocationDTO(
                location.getId(),
                location.getName(),
                location.getCategoryId(),
                location.getUserId(),
                location.isPrivate(),
                location.getCreated(),
                location.getLastModified(),
                location.getDescription(),
                (float) position.getCoordinate(1),
                (float) position.getCoordinate(0),
                location.isDeleted()
        );
    }
}