package org.suspensive.lovepdfnonreactive.domain.ports.input.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.suspensive.lovepdfnonreactive.domain.models.dto.PDFSplitterDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFSplitterException;

public interface SplitPDFUseCase {
    PDFSplitterDTO splitPDF(PDDocument pdf, int page) throws PDFSplitterException;
}
