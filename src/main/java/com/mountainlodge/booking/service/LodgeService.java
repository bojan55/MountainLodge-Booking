package com.mountainlodge.booking.service;

import com.mountainlodge.booking.dto.request.LodgeCreateRequest;
import com.mountainlodge.booking.dto.response.LodgeResponse;
import com.mountainlodge.booking.entity.Lodge;
import com.mountainlodge.booking.entity.User;
import com.mountainlodge.booking.exception.ResourceNotFoundException;
import com.mountainlodge.booking.repository.LodgeRepository;
import com.mountainlodge.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LodgeService {

    private final LodgeRepository lodgeRepository;
    private final UserRepository userRepository;

    public LodgeResponse createLodge(LodgeCreateRequest request){
        User manager = userRepository.findById(request.getManagerId()).
                orElseThrow(() -> new ResourceNotFoundException(
                        "Manager not found with id " + request.getManagerId()
                ));

        Lodge lodge = new Lodge();
        lodge.setName(request.getName());
        lodge.setLocation(request.getLocation());
        lodge.setDescription(request.getDescription());
        lodge.setManager(manager);
        Lodge saved = lodgeRepository.save(lodge);
        return mapToResponseDTO(saved);
    }

    public LodgeResponse getLodgeById(Long id){
        Lodge lodge = lodgeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Lodge not found with id " + id
                ));
        return mapToResponseDTO(lodge);
    }

    public List<LodgeResponse> getAllLodges(){
        return lodgeRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public void deleteLodge(Long id){
        Lodge lodge = lodgeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("" +
                        "Lodge not found with id " + id));
        lodgeRepository.delete(lodge);
    }

    private LodgeResponse mapToResponseDTO(Lodge lodge){
        return new LodgeResponse(
                lodge.getId(),
                lodge.getName(),
                lodge.getLocation(),
                lodge.getDescription(),
                lodge.getManager().getFirstName()+ " " +lodge.getManager().getLastName());
    }
}
