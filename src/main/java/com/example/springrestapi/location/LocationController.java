package com.example.springrestapi.location;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDTO> getAllPublicLocations() {
        return locationService.getAllPublicLocations();
    }

    @GetMapping("/{id}")
    public LocationDTO getPublicLocationById(@PathVariable Integer id) {
        return locationService.getPublicLocationById(id);
    }

    @GetMapping("/categories/{id}")
    public List<LocationDTO> getPublicLocationByCategoryId(@PathVariable Integer id) {
        return locationService.getPublicLocationByCategoryId(id);
    }

    @GetMapping("/all/user")
    @PreAuthorize("isAuthenticated()")
    public List<LocationDTO> getAllPublicLocationsAndUserLocations() {
        return locationService.getAllUserLocations();
    }

    @GetMapping("/area/{lon}/{lat}")
    public List<LocationDTO> getLocationsWithin10km(@PathVariable float lon, @PathVariable float lat) {
        return locationService.getLocationsWithin10km(lon, lat);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> createLocation(@RequestBody LocationDTO locationDTO) {
        int id = locationService.addLocation(locationDTO);
        return ResponseEntity.created(URI.create("/locations/" + id)).build();
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteLocation(@PathVariable("id") Integer id) {
        locationService.softDeleteLocation(id);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updateLocation(@PathVariable("id") Integer id, @RequestBody LocationDTO locationDTO) {
        locationService.updateLocation(id, locationDTO);
        return ResponseEntity.noContent().build();
    }
}