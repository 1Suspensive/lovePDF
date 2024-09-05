package org.suspensive.lovepdfnonreactive.application.usecases.pdf;


import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.dto.PDFSplitterDTO;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFSplitterException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.SplitPDFUseCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SplitPDFUseCaseImpl implements SplitPDFUseCase {
    @Override
    public PDFSplitterDTO splitPDF(PDDocument pdf, int pageToSplit) throws PDFSplitterException {
        List<PDDocument> pdfsSpplited = new ArrayList<>();
        Splitter splitter = new Splitter();
        splitter.setSplitAtPage(pageToSplit);
        try {
            pdfsSpplited.addAll(splitter.split(pdf));

            ByteArrayOutputStream pdf1 = new ByteArrayOutputStream();
            ByteArrayOutputStream pdf2 = new ByteArrayOutputStream();

            pdfsSpplited.get(0).save(pdf1);
            pdfsSpplited.get(1).save(pdf2);

            return new PDFSplitterDTO(pdf1.toByteArray(),pdf2.toByteArray());
        } catch (IOException e) {
            throw new PDFSplitterException("PDF could not be split");
        }finally{
            try {
                pdf.close();
                pdfsSpplited.get(0).close();
                pdfsSpplited.get(1).close();
            } catch (IOException e) {
                throw new PDFSplitterException("PDF could not be closed");
            }
        }

    }
}
