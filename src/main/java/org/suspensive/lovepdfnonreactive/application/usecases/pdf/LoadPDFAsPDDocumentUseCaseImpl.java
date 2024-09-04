package org.suspensive.lovepdfnonreactive.application.usecases.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.MaximumSizeExceededException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotLoadedException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.LoadPDFAsPDDocumentUseCase;

import java.io.IOException;

@Component
public class LoadPDFAsPDDocumentUseCaseImpl implements LoadPDFAsPDDocumentUseCase {

    //Maximum pdf size allowed in bytes
    @Value("${pdf.maximum_size}")
    private int PDF_MAXIMUM_SIZE;

    @Override
    public PDDocument loadPDFAsPDDocument(MultipartFile pdf) throws MaximumSizeExceededException, PDFNotLoadedException, IOException {

        if(pdf.getSize()>PDF_MAXIMUM_SIZE){
            throw new MaximumSizeExceededException("PDF size exceeds the maximum size allowed");
        }
        try {
            //WARNING -> when using this method, the pdf is loaded into memory, so it needs to be closed by another function
            return Loader.loadPDF(pdf.getBytes());
        } catch (IOException e) {
            throw new PDFNotLoadedException("PDF could not be loaded");
        } finally{
            pdf.getInputStream().close();
        }
    }
}
