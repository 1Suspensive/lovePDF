package org.suspensive.lovepdfnonreactive.domain.ports.input.authentication;

import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthResponseDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthSignUpRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserAlreadyExistsException;

public interface SignUpUseCase {
    AuthResponseDTO signUp(AuthSignUpRequestDTO authSignUpRequestDTO) throws UserAlreadyExistsException;
}
