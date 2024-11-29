package com.example.springrestapi.location;

import com.example.springrestapi.location.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByIsPrivateFalseAndDeletedFalse();

    Optional<Location> findByIdAndIsPrivateFalseAndDeletedFalse(Integer id);

    Optional<Location> findByIdAndUserId(Integer id, String userId);

    List<Location> findByCategoryIdAndIsPrivateFalseAndDeletedFalse(Integer categoryId);

    List<Location> findByUserIdAndDeletedFalse(String userId);

    @Query("SELECT l FROM Location l WHERE (l.isPrivate = false AND l.deleted = false AND l.categoryId = :categoryId) OR (l.userId = :userId AND l.deleted = false)")
    List<Location> findByCategoryIdAndIsPrivateFalseAndDeletedFalseOrUserIdAndDeletedFalse(@Param("categoryId") Integer categoryId, @Param("userId") String userId);
}