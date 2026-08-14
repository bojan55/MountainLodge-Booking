package com.mountainlodge.booking.controller;

import com.mountainlodge.booking.dto.request.LodgeCreateRequest;
import com.mountainlodge.booking.dto.response.LodgeResponse;
import com.mountainlodge.booking.service.LodgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lodges")
@RequiredArgsConstructor
public class LodgeController {

    private final LodgeService lodgeService;

    @PostMapping
    public ResponseEntity <LodgeResponse> createLodge(@RequestBody LodgeCreateRequest request){
        LodgeResponse created = lodgeService.createLodge(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LodgeResponse> getLodgeById(@PathVariable Long id){
        return ResponseEntity.ok(lodgeService.getLodgeById(id));
    }

    @GetMapping
    public ResponseEntity<List<LodgeResponse>> getAllLodges(){
        return ResponseEntity.ok(lodgeService.getAllLodges());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLodge(@PathVariable Long id){
        lodgeService.deleteLodge(id);
        return ResponseEntity.noContent().build();
    }

}
