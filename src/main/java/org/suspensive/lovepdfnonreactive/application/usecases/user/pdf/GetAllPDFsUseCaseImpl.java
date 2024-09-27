package org.suspensive.lovepdfnonreactive.application.usecases.user.pdf;

import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.GetAllPDFsUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.output.UserPersistencePort;

import java.util.Map;

@Component
public class GetAllPDFsUseCaseImpl implements GetAllPDFsUseCase {

    private final UserPersistencePort userPersistencePort;

    public GetAllPDFsUseCaseImpl(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public Map<String, byte[]> getAllPDFs(String username) throws UserNotFoundException {
        return userPersistencePort.findUserByUsername(username).getPdfs();
    }
}
