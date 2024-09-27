package org.suspensive.lovepdfnonreactive.application.usecases.user.pdf;

import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.GetPDFByNameUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.output.UserPersistencePort;

@Component
public class GetPDFByNameUseCaseImpl implements GetPDFByNameUseCase {

    private final UserPersistencePort userPersistencePort;

    public GetPDFByNameUseCaseImpl(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public byte[] getPDFByName(String username, String pdfName) throws UserNotFoundException {
        //Returns PDF File, if not found returns null
        return userPersistencePort.findUserByUsername(username).getPdfs().get(pdfName);
    }
}
