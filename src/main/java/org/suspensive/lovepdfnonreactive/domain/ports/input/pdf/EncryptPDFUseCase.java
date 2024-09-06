package org.suspensive.lovepdfnonreactive.domain.ports.input.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

public interface EncryptPDFUseCase {
    byte[] encryptPDF(PDDocument pdf, String password) throws IOException;
}
