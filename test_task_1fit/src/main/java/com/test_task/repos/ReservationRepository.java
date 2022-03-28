package com.test_task.repos;

import com.test_task.model.AmenityType;
import com.test_task.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//The Repos folder contains the code for the data access layer,
// namely our repositories. We will use JPA methods to retrieve our data,
// which are pre-made query methods you can use by defining them inside the repository interface.

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationsByAmenityType(AmenityType amenityType);

    List<Reservation> findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween
            (LocalDate reservationDate, LocalTime startTime, LocalTime endTime, LocalTime betweenStart, LocalTime betweenEnd);
}
