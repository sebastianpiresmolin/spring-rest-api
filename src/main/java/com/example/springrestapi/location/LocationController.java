package com.example.springrestapi.location;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/locations/{id}")
    public LocationDTO getPublicLocationById(@PathVariable Integer id) {
        return locationService.getPublicLocationById(id);
    }

    @GetMapping("/locations/categories/{id}")
    public List<LocationDTO> getPublicLocationByCategoryId(@PathVariable Integer id) {
        return locationService.getPublicLocationByCategoryId(id);
    }

    @GetMapping("/locations/all/user")
    public List<LocationDTO> getAllPublicLocationsAndUserLocations() {
        return locationService.getAllUserLocations();
    }

    @PostMapping("/locations")
    public ResponseEntity<Void> createLocation(@RequestBody LocationDTO locationDTO) {
        int id = locationService.addLocation(locationDTO);
        return ResponseEntity.created(URI.create("/locations/" + id)).build();
    }

    @PutMapping("/locations/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteLocation(@PathVariable("id") Integer id) {
        locationService.softDeleteLocation(id);
    }

    @PutMapping("/locations/edit/{id}")
    public ResponseEntity<Void> updateLocation(@PathVariable("id") Integer id, @RequestBody LocationDTO locationDTO) {
        locationService.updateLocation(id, locationDTO);
        return ResponseEntity.noContent().build();
    }
}