package org.suspensive.lovepdfnonreactive.domain.models.exceptions;

public class PDFNotEncryptedException extends Exception {
    public PDFNotEncryptedException() {
        super("The PDF is not encrypted");
    }
}
