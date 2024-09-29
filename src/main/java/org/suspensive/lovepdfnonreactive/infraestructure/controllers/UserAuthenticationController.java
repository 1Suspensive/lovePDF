package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.suspensive.lovepdfnonreactive.application.services.UserAuthenticationService;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthLoginRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthResponseDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthSignUpRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserAlreadyExistsException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/auth")
@Validated
public class UserAuthenticationController {

    private final UserAuthenticationService userAuthenticationService;

    public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthLoginRequestDTO loginRequest) throws UserNotFoundException, BadCredentialsException {
        return new ResponseEntity<>(userAuthenticationService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDTO> signUp(@Valid @RequestBody AuthSignUpRequestDTO request) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userAuthenticationService.signUp(request), HttpStatus.CREATED);
    }

}
