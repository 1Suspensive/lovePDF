package org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf;

import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

public interface DeletePDFByNameUseCase {
    boolean deletePDFByName(String username, String pdfName) throws UserNotFoundException;
}
