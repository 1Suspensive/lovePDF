package org.suspensive.lovepdfnonreactive.application.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.MaximumSizeExceededException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFMergerException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotLoadedException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.LoadPDFAsPDDocumentUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.MergePDFsUseCase;

import java.io.IOException;

@Service
public class PDFService {

    private final LoadPDFAsPDDocumentUseCase loadPDFUseCase;
    private final MergePDFsUseCase mergePDFsUseCase;

    public PDFService(LoadPDFAsPDDocumentUseCase loadPDFAsPDDocumentUseCase, MergePDFsUseCase mergePDFsUseCase) {
        this.loadPDFUseCase = loadPDFAsPDDocumentUseCase;
        this.mergePDFsUseCase = mergePDFsUseCase;
    }

    public byte[] mergePDFs(MultipartFile pdf1, MultipartFile pdf2) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFMergerException {
        return mergePDFsUseCase.mergePDFs(loadPDFUseCase.loadPDFAsPDDocument(pdf1),loadPDFUseCase.loadPDFAsPDDocument(pdf2));
    }
}
