package com.mountainlodge.booking.service;

import com.mountainlodge.booking.dto.request.RoomCreateRequest;
import com.mountainlodge.booking.dto.response.RoomResponse;
import com.mountainlodge.booking.entity.Lodge;
import com.mountainlodge.booking.entity.Room;
import com.mountainlodge.booking.exception.ResourceNotFoundException;
import com.mountainlodge.booking.repository.LodgeRepository;
import com.mountainlodge.booking.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final LodgeRepository lodgeRepository;

    public RoomResponse createRoom(RoomCreateRequest request){
        Lodge lodge = lodgeRepository.findById(request.getLodgeId())
                .orElseThrow(() -> new ResourceNotFoundException("" +
                        "Lodge not found with id " + request.getLodgeId()));

        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());
        room.setLodge(lodge);
        Room created = roomRepository.save(room);
        return mapToResponseDTO(created);
    }

    public RoomResponse getRoomById(Long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Room not found with id " + id
                ));
        return mapToResponseDTO(room);
    }

    public List<RoomResponse> getAllRooms(){
        return roomRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public List<RoomResponse> getRoomsByLodgeId(Long lodgeId){
        return roomRepository.findByLodgeId(lodgeId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public void deleteRoom(Long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Room not found with id " + id
                ));
        roomRepository.delete(room);
    }

    private RoomResponse mapToResponseDTO(Room room){
        return new RoomResponse(
                room.getId(),
                room.getRoomNumber(),
                room.getCapacity(),
                room.getLodge().getName()
        );
    }
}
