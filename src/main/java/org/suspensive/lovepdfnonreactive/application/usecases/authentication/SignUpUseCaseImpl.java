package org.suspensive.lovepdfnonreactive.application.usecases.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.User;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthResponseDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthSignUpRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserAlreadyExistsException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.authentication.SignUpUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.output.JwtPort;
import org.suspensive.lovepdfnonreactive.domain.ports.output.UserPersistencePort;
import org.suspensive.lovepdfnonreactive.domain.models.Role;

import java.util.Set;

@Component
public class SignUpUseCaseImpl implements SignUpUseCase {

    private final JwtPort jwtPort;
    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public SignUpUseCaseImpl(JwtPort jwtPort, UserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.jwtPort = jwtPort;
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AuthResponseDTO signUp(AuthSignUpRequestDTO authSignUpRequestDTO) throws UserAlreadyExistsException {
        try {
            // Check if the user already exists
            userPersistencePort.findUserByEmail(authSignUpRequestDTO.email());
            throw new UserAlreadyExistsException("User with email: " + authSignUpRequestDTO.email() + " already exists");
        } catch (UserNotFoundException e) {
            User userSaved = userPersistencePort.saveUser(User.builder()
                            .id(null)
                            .username(authSignUpRequestDTO.username())
                            .password(passwordEncoder.encode(authSignUpRequestDTO.password()))
                            .email(authSignUpRequestDTO.email())
                            .roles(Set.of(Role.DEFAULT_ROLE))
                            .status(true)
                    .build());
            return new AuthResponseDTO(userSaved.getUsername(), "User created successfully", jwtPort.generateToken(userSaved), true);
        }
    }
}
