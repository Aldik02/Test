package com.test_task.service;

import com.test_task.exception.CapacityFullException;
import com.test_task.model.Reservation;
import com.test_task.repos.CapacityRepository;
import com.test_task.repos.ReservationRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
//Services will hold the logic of our application.
// The best practice is to keep controllers thin by keeping the business logic in a separate place, the service classes.

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CapacityRepository capacityRepository;

    public ReservationService(final ReservationRepository reservationRepository,
                              final CapacityRepository capacityRepository) {
        this.reservationRepository = reservationRepository;
        this.capacityRepository = capacityRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation get(final Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final Reservation reservation) {
        int capacity = capacityRepository.findByAmenityType(reservation.getAmenityType()).getCapacity();
        int overlappingReservations = reservationRepository
                .findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
                        reservation.getReservationDate(),
                        reservation.getStartTime(), reservation.getEndTime(),
                        reservation.getStartTime(), reservation.getEndTime()).size();

        if (overlappingReservations >= capacity) {
            throw new CapacityFullException("This amenity's capacity is full at desired time");
        }

        return reservationRepository.save(reservation).getId();
    }

    public void update(final Long id, final Reservation reservation) {
        final Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reservationRepository.save(reservation);
    }

    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }

}