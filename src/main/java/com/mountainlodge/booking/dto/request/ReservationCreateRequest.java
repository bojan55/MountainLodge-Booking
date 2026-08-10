package com.mountainlodge.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateRequest {

    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
}
