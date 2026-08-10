package com.mountainlodge.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgeCreateRequest {

    private String name;
    private String description;
    private String location;
    private Long managerId;
}
