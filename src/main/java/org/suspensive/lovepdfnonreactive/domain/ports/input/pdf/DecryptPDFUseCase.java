package org.suspensive.lovepdfnonreactive.domain.ports.input.pdf;

import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotLoadedException;

import java.io.IOException;

public interface DecryptPDFUseCase {
    byte[] decryptPDF(MultipartFile pdf, String password) throws PDFNotLoadedException, IOException;
}
