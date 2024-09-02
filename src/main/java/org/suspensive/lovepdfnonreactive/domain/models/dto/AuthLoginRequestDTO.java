package org.suspensive.lovepdfnonreactive.domain.models.dto;

import io.micrometer.common.lang.NonNull;

public record AuthLoginRequestDTO(@NonNull String email,
                                  @NonNull String password) {
}
