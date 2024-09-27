package org.suspensive.lovepdfnonreactive.application.usecases.user.pdf;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.User;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.UploadPDFUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.output.UserPersistencePort;

import java.io.IOException;

@Component
public class UploadPDFUseCaseImpl implements UploadPDFUseCase {
    private final UserPersistencePort userPersistencePort;

    public UploadPDFUseCaseImpl(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public boolean uploadPDF(String username, MultipartFile pdfFile) throws IOException, UserNotFoundException {
        String fileName = pdfFile.getOriginalFilename();
        byte[] pdf = pdfFile.getBytes();
        User user = userPersistencePort.findUserByUsername(username);
        if (user.getPdfs().containsKey(fileName)) {
            return false;
        }
        user.getPdfs().put(fileName, pdf);
        userPersistencePort.saveUser(user);
        return true;
    }
}
