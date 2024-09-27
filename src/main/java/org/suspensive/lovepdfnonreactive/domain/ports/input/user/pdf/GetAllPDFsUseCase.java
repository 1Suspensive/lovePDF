package org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf;

import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

import java.util.Map;

public interface GetAllPDFsUseCase {
    Map<String,byte[]> getAllPDFs(String username) throws UserNotFoundException;
}
