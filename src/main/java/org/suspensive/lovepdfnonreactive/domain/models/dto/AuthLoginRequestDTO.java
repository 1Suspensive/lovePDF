package org.suspensive.lovepdfnonreactive.domain.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(@Email @NotBlank String email,
                                   @NotBlank String password) {
}
