package org.suspensive.lovepdfnonreactive.application.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.*;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.*;
import org.suspensive.lovepdfnonreactive.domain.ports.input.utils.LoadPDFAsPDDocumentUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.utils.CreateZIPFileUseCase;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PDFServiceBasicFunctionsService {

    private final LoadPDFAsPDDocumentUseCase loadPDFUseCase;
    private final MergePDFsUseCase mergePDFsUseCase;
    private final SplitPDFUseCase splitPDFUseCase;
    private final CreateZIPFileUseCase createZIPFileUseCase;
    private final RemovePDFPagesUseCase removePDFPagesUseCase;
    private final EncryptPDFUseCase encryptPDFUseCase;
    private final DecryptPDFUseCase decryptPDFUseCase;

    //Returns a merged pdf
    public byte[] mergePDFs(MultipartFile pdf1, MultipartFile pdf2) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFMergerException {
        return mergePDFsUseCase.mergePDFs(loadPDFUseCase.loadPDFAsPDDocument(pdf1),loadPDFUseCase.loadPDFAsPDDocument(pdf2));
    }

    //Returns a zip file with the split pdfs
    public byte[] splitPDF(MultipartFile pdf, int pageToSplit) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFSplitterException {
        return createZIPFileUseCase.createZIP(splitPDFUseCase.splitPDF(loadPDFUseCase.loadPDFAsPDDocument(pdf),pageToSplit));
    }

    //Returns a pdf with the pages removed
    public byte[] removePDFPages(MultipartFile pdf, int[] pagesToRemove) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFRemovePagesException {
        return removePDFPagesUseCase.removePDFPages(loadPDFUseCase.loadPDFAsPDDocument(pdf),pagesToRemove);
    }

    //Returns a pdf encrypted with the password
    //When a user opens the pdf with the password, all the following permissions are granted:
//    - print the document
//    - modify the content of the document
//    - copy or extract content of the document
//    - add or modify annotations
//    - fill in interactive form fields
//    - extract text and graphics for accessibility to visually impaired people
//    - assemble the document
//    - print in degraded quality
    public byte[] encryptPDF(MultipartFile pdf, String password) throws MaximumSizeExceededException, IOException, PDFNotLoadedException {
        return encryptPDFUseCase.encryptPDF(loadPDFUseCase.loadPDFAsPDDocument(pdf),password);
    }

    public byte[] decryptPDF(MultipartFile pdf, String password) throws PDFNotLoadedException, IOException {
        return decryptPDFUseCase.decryptPDF(pdf,password);
    }


}
