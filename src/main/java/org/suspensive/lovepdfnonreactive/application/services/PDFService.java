package org.suspensive.lovepdfnonreactive.application.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.MaximumSizeExceededException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFMergerException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotLoadedException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFSplitterException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.LoadPDFAsPDDocumentUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.MergePDFsUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.SplitPDFUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.utils.CreateZIPFileUseCase;

import java.io.IOException;

@Service
public class PDFService {

    private final LoadPDFAsPDDocumentUseCase loadPDFUseCase;
    private final MergePDFsUseCase mergePDFsUseCase;
    private final SplitPDFUseCase splitPDFUseCase;
    private final CreateZIPFileUseCase createZIPFileUseCase;

    public PDFService(LoadPDFAsPDDocumentUseCase loadPDFAsPDDocumentUseCase, MergePDFsUseCase mergePDFsUseCase, SplitPDFUseCase splitPDFUseCase, CreateZIPFileUseCase createZIPFileUseCase) {
        this.loadPDFUseCase = loadPDFAsPDDocumentUseCase;
        this.mergePDFsUseCase = mergePDFsUseCase;
        this.splitPDFUseCase = splitPDFUseCase;
        this.createZIPFileUseCase = createZIPFileUseCase;
    }

    //Returns a merged pdf
    public byte[] mergePDFs(MultipartFile pdf1, MultipartFile pdf2) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFMergerException {
        return mergePDFsUseCase.mergePDFs(loadPDFUseCase.loadPDFAsPDDocument(pdf1),loadPDFUseCase.loadPDFAsPDDocument(pdf2));
    }

    //Returns a zip file with the split pdfs
    public byte[] splitPDF(MultipartFile pdf, int pageToSplit) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFSplitterException {
        return createZIPFileUseCase.createZIP(splitPDFUseCase.splitPDF(loadPDFUseCase.loadPDFAsPDDocument(pdf),pageToSplit));
    }


}
