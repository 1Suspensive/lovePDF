package org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf;

import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

public interface GetPDFByNameUseCase {
    byte[] getPDFByName(String username, String pdfName) throws UserNotFoundException;
}
