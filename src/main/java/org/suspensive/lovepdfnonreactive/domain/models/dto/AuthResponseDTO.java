package org.suspensive.lovepdfnonreactive.domain.models.dto;

public record AuthResponseDTO(String username, String message, String token, boolean authenticated) {
}
