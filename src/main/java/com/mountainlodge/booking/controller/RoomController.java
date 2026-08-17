package com.mountainlodge.booking.controller;

import com.mountainlodge.booking.dto.request.RoomCreateRequest;
import com.mountainlodge.booking.dto.response.RoomResponse;
import com.mountainlodge.booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomCreateRequest request){
        RoomResponse created = roomService.createRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id){
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms(){
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/lodge/{lodgeId}")
    public ResponseEntity<List<RoomResponse>> getRoomsByLodgeId(@PathVariable Long lodgeId){
        return ResponseEntity.ok(roomService.getRoomsByLodgeId(lodgeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

}
