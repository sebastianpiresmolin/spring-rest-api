package com.example.springrestapi.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public List<LocationDTO> getAllPublicLocations() {
        return locationService.getAllPublicLocations();
    }

    @PostMapping("/locations")
    public ResponseEntity<Void> createPerson(@RequestBody LocationDTO locationDTO) {
        int id = locationService.addLocation(locationDTO);
        return ResponseEntity.created(URI.create("/locations/" + id)).build();
    }
}