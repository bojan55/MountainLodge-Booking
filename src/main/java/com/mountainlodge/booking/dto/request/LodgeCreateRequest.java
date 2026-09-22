package com.mountainlodge.booking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgeCreateRequest {

    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String location;
    @NotNull
    private Long managerId;
}
