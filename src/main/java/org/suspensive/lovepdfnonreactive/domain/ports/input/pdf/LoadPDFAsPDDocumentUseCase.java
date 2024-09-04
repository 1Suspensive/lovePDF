package org.suspensive.lovepdfnonreactive.domain.ports.input.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.MaximumSizeExceededException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotLoadedException;

import java.io.IOException;

public interface LoadPDFAsPDDocumentUseCase {
    PDDocument loadPDFAsPDDocument(MultipartFile pdf) throws MaximumSizeExceededException, PDFNotLoadedException, IOException;
}
