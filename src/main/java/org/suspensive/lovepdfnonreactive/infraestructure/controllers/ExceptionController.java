package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.*;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MaximumSizeExceededException.class})
    public ResponseEntity<String> handleMaximumSizeExceededException(MaximumSizeExceededException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PDFMergerException.class})
    public ResponseEntity<String> handlePDFMergerException(PDFMergerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PDFNotLoadedException.class})
    public ResponseEntity<String> handlePDFNotLoadedException(PDFNotLoadedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PDFSplitterException.class})
    public ResponseEntity<String> handlePDFSplitterException(PDFSplitterException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PDFRemovePagesException.class})
    public ResponseEntity<String> handlePDFRemovePagesException(PDFRemovePagesException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PDFNotFoundException.class})
    public ResponseEntity<String> handlePDFNotFoundException(PDFNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
