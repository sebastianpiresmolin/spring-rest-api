package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LocationService {

    LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) { this.locationRepository = locationRepository; }

    public List<LocationDTO> getAllPublicLocations() {
        return locationRepository.findByIsPrivateFalse()
                .stream()
                .map(LocationDTO::fromLocation)
                .toList();
    }

    public LocationDTO getPublicLocationById(Integer id) {
        return locationRepository.findByIdAndIsPrivateFalse(id)
                .map(LocationDTO::fromLocation)
                .orElseThrow(() -> new IllegalArgumentException("Location not found or is private"));
    }

    public int addLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.name());
        location.setCategoryId(locationDTO.categoryId());
        location.setUserId(locationDTO.userId());
        location.setPrivate(locationDTO.isPrivate());
        location.setCreated(locationDTO.created());
        location.setLastModified(locationDTO.lastModified());
        location.setDescription(locationDTO.description());
        location.setLongitude(locationDTO.longitude());
        location.setLatitude(locationDTO.latitude());
        location.setDeleted(locationDTO.isDeleted());

        location = locationRepository.save(location);
        return location.getId();
    }
}