package com.test_task.repos;

import com.test_task.model.AmenityType;
import com.test_task.model.Capacity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityRepository extends JpaRepository<Capacity, Long> {

    Capacity findByAmenityType(AmenityType amenityType);
}
