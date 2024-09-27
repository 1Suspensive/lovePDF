package org.suspensive.lovepdfnonreactive.domain.models.exceptions;

public class PDFAlreadyExistsException extends Exception {
    public PDFAlreadyExistsException(String message) {
        super(message);
    }
}
