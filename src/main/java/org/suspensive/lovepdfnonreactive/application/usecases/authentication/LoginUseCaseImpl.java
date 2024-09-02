package org.suspensive.lovepdfnonreactive.application.usecases.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthResponseDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.authentication.LoginUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.output.JwtPort;

@Component
public class LoginUseCaseImpl implements LoginUseCase {

    private final JwtPort jwtPort;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCaseImpl(JwtPort jwtPort, PasswordEncoder passwordEncoder) {
        this.jwtPort = jwtPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO login(UserDetails userFound, String password) throws UserNotFoundException {
        if(userFound == null){
            throw new UserNotFoundException("User not found");
        }
        if(!passwordEncoder.matches(password, userFound.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new AuthResponseDTO(userFound.getUsername(), "Login successful",jwtPort.generateToken(userFound),true);
    }
}
