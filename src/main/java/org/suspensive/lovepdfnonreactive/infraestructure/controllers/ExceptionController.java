package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.*;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MaximumSizeExceededException.class})
    public ResponseEntity<Map<String, String>> handleMaximumSizeExceededException(MaximumSizeExceededException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler({PDFMergerException.class})
    public ResponseEntity<Map<String, String>> handlePDFMergerException(PDFMergerException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PDFNotLoadedException.class})
    public ResponseEntity<Map<String, String>> handlePDFNotLoadedException(PDFNotLoadedException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PDFSplitterException.class})
    public ResponseEntity<Map<String, String>> handlePDFSplitterException(PDFSplitterException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PDFRemovePagesException.class})
    public ResponseEntity<Map<String, String>> handlePDFRemovePagesException(PDFRemovePagesException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PDFNotFoundException.class})
    public ResponseEntity<Map<String, String>> handlePDFNotFoundException(PDFNotFoundException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PDFAlreadyExistsException.class})
    public ResponseEntity<Map<String, String>> handlePDFAlreadyExistsException(PDFAlreadyExistsException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SignatureVerificationException.class})
    public ResponseEntity<Map<String, String>> handleSignatureVerificationException(SignatureVerificationException e) {
        return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
