package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;

import org.geolatte.geom.Point;
import org.geolatte.geom.crs.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.geolatte.geom.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;


@Service
public class LocationService {

    LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> getAllPublicLocations() {
        return locationRepository.findByIsPrivateFalseAndDeletedFalse()
                .stream()
                .map(LocationDTO::fromLocation)
                .toList();
    }

    public LocationDTO getPublicLocationById(Integer id) {
        return locationRepository.findByIdAndIsPrivateFalseAndDeletedFalse(id)
                .map(LocationDTO::fromLocation)
                .orElseThrow(() -> new IllegalArgumentException("Location not found or is private"));
    }

    public List<LocationDTO> getPublicLocationByCategoryId(Integer categoryId) {
        List<LocationDTO> locations = locationRepository.findByCategoryIdAndIsPrivateFalseAndDeletedFalse(categoryId)
                .stream()
                .map(LocationDTO::fromLocation)
                .collect(Collectors.toList());

        if (locations.isEmpty()) {
            throw new IllegalArgumentException("No Category with provided ID found or all locations are private.");
        }

        return locations;
    }

    public List<LocationDTO> getAllUserLocations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        List<Location> locations = locationRepository.findByUserIdAndDeletedFalse(currentUsername);
        return locations.stream()
                .map(LocationDTO::fromLocation)
                .collect(Collectors.toList());
    }

    private double calculateDistance(G2D point1, G2D point2) {
        final int R = 6371;

        double latDistance = Math.toRadians(point2.getLat() - point1.getLat());
        double lonDistance = Math.toRadians(point2.getLon() - point1.getLon());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(point1.getLat())) * Math.cos(Math.toRadians(point2.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c * 1000;
    }

    public List<LocationDTO> getLocationsWithin10km(float longitude, float latitude) {
        List<Location> allLocations = locationRepository.findByIsPrivateFalseAndDeletedFalse();
        List<LocationDTO> filteredLocationDTOs = new ArrayList<>();

        G2D refPoint = new G2D(longitude, latitude);

        for (Location location : allLocations) {
            G2D locPoint = location.getCoordinate().getPosition();
            double distance = calculateDistance(refPoint, locPoint);

            if (distance <= 10_000) {
                filteredLocationDTOs.add(LocationDTO.fromLocation(location));
            }
        }

        return filteredLocationDTOs;
    }

    public int addLocation(LocationDTO locationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Location location = new Location();
        location.setName(locationDTO.name());
        location.setCategoryId(locationDTO.categoryId());
        location.setUserId(currentUsername);
        location.setPrivate(locationDTO.isPrivate());
        location.setDescription(locationDTO.description());


        float lat = locationDTO.latitude();
        float lon = locationDTO.longitude();


        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Invalid latitude or longitude");
        }


        var geo = Geometries.mkPoint(new G2D(lon, lat), WGS84);
        location.setCoordinate(geo);

        try {
            location = locationRepository.save(location);
            return location.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Transactional
    public void softDeleteLocation(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Location location = locationRepository.findByIdAndUserId(id, currentUsername)
                .orElseThrow(() -> new IllegalArgumentException("Unable to find location with id " + id + " for user " + currentUsername));

        location.setDeleted(true);
        locationRepository.save(location);
    }

    @Transactional
    public void updateLocation(Integer id, LocationDTO locationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Location location = locationRepository.findByIdAndUserId(id, currentUsername)
                .orElseThrow(() -> new IllegalArgumentException("Unable to find location with id " + id + " for user " + currentUsername));

        location.setName(locationDTO.name());
        location.setPrivate(locationDTO.isPrivate());
        location.setDescription(locationDTO.description());
        locationRepository.save(location);
    }
}