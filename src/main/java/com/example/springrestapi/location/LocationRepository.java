package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByIsPrivateFalse();

    Optional<Location> findByIdAndIsPrivateFalse(Integer id);

    List<Location> findByCategoryIdAndIsPrivateFalse(Integer categoryId);
}