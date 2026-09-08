package com.mountainlodge.booking.scheduler;

import com.mountainlodge.booking.entity.Reservation;
import com.mountainlodge.booking.enums.ReservationStatus;
import com.mountainlodge.booking.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PendingReservationScheduler {

    private final ReservationRepository reservationRepository;

    @Transactional
    @Scheduled(fixedRate = 3600000)
    public void cancelExpiredPendingReservations(){
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        List<Reservation> reservations = reservationRepository.findByReservationStatusAndCreatedAtBefore(ReservationStatus.PENDING, cutoff);
        for (Reservation reservation : reservations){
            reservation.setReservationStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
        }
    }
}
