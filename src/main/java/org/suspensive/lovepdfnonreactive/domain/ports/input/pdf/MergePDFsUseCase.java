package org.suspensive.lovepdfnonreactive.domain.ports.input.pdf;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFMergerException;

public interface MergePDFsUseCase {
    byte[] mergePDFs(PDDocument pdf1, PDDocument pdf2) throws PDFMergerException;
}
