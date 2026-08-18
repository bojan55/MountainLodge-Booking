package com.mountainlodge.booking.service;

import com.mountainlodge.booking.dto.request.ReservationCreateRequest;
import com.mountainlodge.booking.dto.response.ReservationResponse;
import com.mountainlodge.booking.entity.Reservation;
import com.mountainlodge.booking.entity.Room;
import com.mountainlodge.booking.entity.User;
import com.mountainlodge.booking.enums.ReservationStatus;
import com.mountainlodge.booking.exception.ResourceNotFoundException;
import com.mountainlodge.booking.repository.ReservationRepository;
import com.mountainlodge.booking.repository.RoomRepository;
import com.mountainlodge.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;


    public ReservationResponse createReservation(ReservationCreateRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id " + request.getUserId()));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Room not found with id " + request.getRoomId()));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setReservationStatus(ReservationStatus.PENDING);

        Reservation created = reservationRepository.save(reservation);
        return mapToResponseDTO(created);
    }

    public ReservationResponse getReservationById(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with id " + id
                ));
        return mapToResponseDTO(reservation);
    }

    public List<ReservationResponse> getAllReservations(){
        return reservationRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public List<ReservationResponse> getReservationsByUserId(Long userId){
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public ReservationResponse cancelReservation(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with id " + id
                ));
        reservation.setReservationStatus(ReservationStatus.CANCELLED);
        Reservation updated = reservationRepository.save(reservation);
        return mapToResponseDTO(updated);
    }


    private ReservationResponse mapToResponseDTO(Reservation reservation){
        return new ReservationResponse(
                reservation.getId(),
                reservation.getRoom().getRoomNumber(),
                reservation.getRoom().getLodge().getName(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getReservationStatus(),
                reservation.getCreatedAt()
        );
    }
}
