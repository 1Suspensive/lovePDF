package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suspensive.lovepdfnonreactive.application.services.UserAuthenticationService;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthLoginRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthResponseDTO;
import org.suspensive.lovepdfnonreactive.domain.models.dto.AuthSignUpRequestDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserAlreadyExistsException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    private final UserAuthenticationService userAuthenticationService;

    public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthLoginRequestDTO request) throws UserNotFoundException {
        return new ResponseEntity<>(userAuthenticationService.login(request), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody AuthSignUpRequestDTO request) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userAuthenticationService.signUp(request), HttpStatus.CREATED);
    }
}
