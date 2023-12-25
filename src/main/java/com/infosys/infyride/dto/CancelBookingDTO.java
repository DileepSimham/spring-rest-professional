package com.infosys.infyride.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelBookingDTO {

    @NotBlank(message = "{ride.reasonforcancellation.notpresent}")
    @Pattern(regexp = "(?=.*[a-zA-Z0-9].*)[a-zA-Z0-9.,!]*",message = "{ride.reasonforcancellation.invalid}")
    private String reasonForCancellation;
}
