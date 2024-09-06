package org.suspensive.lovepdfnonreactive.domain.ports.input.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFRemovePagesException;

public interface RemovePDFPagesUseCase {
    byte[] removePDFPages(PDDocument pdf, int[] pagesToRemove) throws PDFRemovePagesException;
}
