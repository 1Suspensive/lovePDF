package org.suspensive.lovepdfnonreactive.application.usecases.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.EncryptPDFUseCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class EncryptPDFUseCaseImpl implements EncryptPDFUseCase {

    @Override
    public byte[] encryptPDF(PDDocument pdf, String password) throws IOException {
        pdf.protect(new StandardProtectionPolicy(password, password, new AccessPermission()));
        ByteArrayOutputStream pdfEncrypted = new ByteArrayOutputStream();
        pdf.save(pdfEncrypted);
        pdf.close();
        return pdfEncrypted.toByteArray();
    }
}
