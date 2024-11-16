package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public int addLocation(LocationDTO locationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Location location = new Location();
        location.setName(locationDTO.name());
        location.setCategoryId(locationDTO.categoryId());
        location.setUserId(currentUsername);
        location.setPrivate(locationDTO.isPrivate());
        location.setDescription(locationDTO.description());
        location.setLongitude(locationDTO.longitude());
        location.setLatitude(locationDTO.latitude());



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
}