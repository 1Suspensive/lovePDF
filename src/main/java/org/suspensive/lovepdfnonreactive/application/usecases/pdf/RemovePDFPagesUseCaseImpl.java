package org.suspensive.lovepdfnonreactive.application.usecases.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFRemovePagesException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.RemovePDFPagesUseCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class RemovePDFPagesUseCaseImpl implements RemovePDFPagesUseCase {

    @Override
    public byte[] removePDFPages(PDDocument pdf, int[] pagesToRemove) throws PDFRemovePagesException {

        ByteArrayOutputStream pdfResult = new ByteArrayOutputStream();

        for(int page : pagesToRemove){
            pdf.removePage(page);
        }

        try {
            pdf.save(pdfResult);
            return pdfResult.toByteArray();
        } catch (IOException e) {
            throw new PDFRemovePagesException("Error removing pages from pdf");
        }finally{
            try {
                pdf.close();
            } catch (IOException e) {
                throw new PDFRemovePagesException("Error closing pdf");
            }
        }
    }

}
