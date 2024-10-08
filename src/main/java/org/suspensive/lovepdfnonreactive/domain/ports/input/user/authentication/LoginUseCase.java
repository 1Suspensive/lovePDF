package org.suspensive.lovepdfnonreactive.domain.ports.input.user.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthResponseDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

public interface LoginUseCase {
    AuthResponseDTO login(UserDetails userFound, String password) throws UserNotFoundException, BadCredentialsException;
}
