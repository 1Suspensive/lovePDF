package org.suspensive.lovepdfnonreactive.domain.ports.input.utils;

import org.suspensive.lovepdfnonreactive.domain.models.dto.PDFSplitterDTO;

import java.io.IOException;

//Use case to create a zip file with pdfs
public interface CreateZIPFileUseCase {
    byte[] createZIP(PDFSplitterDTO pdfs) throws IOException;
}
