package org.suspensive.lovepdfnonreactive.application.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthLoginRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthResponseDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthSignUpRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserAlreadyExistsException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.authentication.LoginUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.authentication.SignUpUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.output.UserPersistencePort;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private final UserPersistencePort userPersistencePort;
    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;


    public UserAuthenticationService(UserPersistencePort userPersistencePort, SignUpUseCase signUpUseCase, LoginUseCase loginUseCase) {
        this.userPersistencePort = userPersistencePort;
        this.signUpUseCase = signUpUseCase;
        this.loginUseCase = loginUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        try {
            return userPersistencePort.findUserByEmail(email);
        } catch (UserNotFoundException e) {
            return null;
        }
    }

    public AuthResponseDTO signUp(AuthSignUpRequestDTO request) throws UserAlreadyExistsException {
        return signUpUseCase.signUp(request);
    }

    public AuthResponseDTO login(AuthLoginRequestDTO request) throws UserNotFoundException {
        return loginUseCase.login(loadUserByUsername(request.email()), request.password());
    }

}
