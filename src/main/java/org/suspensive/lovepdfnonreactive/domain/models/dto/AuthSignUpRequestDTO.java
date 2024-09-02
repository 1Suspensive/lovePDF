package org.suspensive.lovepdfnonreactive.domain.models.dto;

import io.micrometer.common.lang.NonNull;

public record AuthSignUpRequestDTO(@NonNull String username,
                                   @NonNull String password,
                                   @NonNull String email) {
}
