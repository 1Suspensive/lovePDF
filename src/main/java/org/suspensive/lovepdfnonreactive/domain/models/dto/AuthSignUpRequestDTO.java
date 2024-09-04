package org.suspensive.lovepdfnonreactive.domain.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthSignUpRequestDTO(@NotBlank String username,
                                   @NotBlank String password,
                                   @Email @NotBlank String email) {
}
