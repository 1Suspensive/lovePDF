package org.suspensive.lovepdfnonreactive.application.usecases.pdf;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFMergerException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.MergePDFsUseCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class MergePDFsUseCaseImpl implements MergePDFsUseCase {
    @Override
    public byte[] mergePDFs(PDDocument pdf1, PDDocument pdf2) throws PDFMergerException {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        try {
            ByteArrayOutputStream pdfResult = new ByteArrayOutputStream();
            pdfMerger.appendDocument(pdf1, pdf2);
            pdf1.save(pdfResult);
            return pdfResult.toByteArray();
        } catch (IOException e) {
            throw new PDFMergerException("PDFs could not be merged");
        }finally {
            try {
                pdf1.close();
                pdf2.close();
            } catch (IOException e) {
                throw new PDFMergerException("PDFs could not be closed");
            }
        }
    }
}
