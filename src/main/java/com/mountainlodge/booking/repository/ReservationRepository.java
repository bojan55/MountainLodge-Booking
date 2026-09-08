package com.mountainlodge.booking.repository;

import com.mountainlodge.booking.entity.Reservation;
import com.mountainlodge.booking.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByRoomId(Long roomId);

    @Query("""
                 SELECT r FROM Reservation r
                 WHERE r.room.id = :roomId
                 AND r.reservationStatus <> 'CANCELLED'
                 AND r.startDate <= :endDate
                 AND r.endDate >= :startDate
""")
    List<Reservation> findOverlappingReservations(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Reservation> findByReservationStatusAndCreatedAtBefore(
            ReservationStatus status, LocalDateTime cutoff
    );
}
