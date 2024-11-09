package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationService {

    LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) { this.locationRepository = locationRepository; }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(LocationDTO::fromLocation)
                .toList();
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

        location.setCoordinates(locationDTO.longitude(), locationDTO.latitude());

        location = locationRepository.save(location);
        return location.getId();
    }
}