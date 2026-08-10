package com.mountainlodge.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreateRequest {

    private Long roomNumber;
    private Long capacity;
    private Long lodgeId;
}
