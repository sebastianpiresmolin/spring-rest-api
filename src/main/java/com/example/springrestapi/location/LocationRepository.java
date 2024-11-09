package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;
import org.springframework.data.repository.ListCrudRepository;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
}