package com.mountainlodge.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgeResponse {

    private Long id;
    private String name;
    private String location;
    private String description;
    private String managerName;
}
