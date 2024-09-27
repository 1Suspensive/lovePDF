package org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf;

import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

import java.io.IOException;

public interface UploadPDFUseCase {
    boolean uploadPDF(String username, MultipartFile pdfFile) throws IOException, UserNotFoundException;
}
