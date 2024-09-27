package org.suspensive.lovepdfnonreactive.application.usecases.user.pdf;

import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.User;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.DeletePDFByNameUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.output.UserPersistencePort;

@Component
public class DeletePDFByNameUseCaseImpl implements DeletePDFByNameUseCase {

    private final UserPersistencePort userPersistencePort;

    public DeletePDFByNameUseCaseImpl(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public boolean deletePDFByName(String username, String pdfName) throws UserNotFoundException{
        User user = userPersistencePort.findUserByUsername(username);
        Object result = user.getPdfs().remove(pdfName);
        //returns false if PDF not found
        if(result == null){
            return false;
        }
        userPersistencePort.saveUser(user);
        return true;
    }
}
