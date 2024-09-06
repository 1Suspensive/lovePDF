package org.suspensive.lovepdfnonreactive.application.usecases.pdf;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotEncryptedException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotLoadedException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.pdf.DecryptPDFUseCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class DecryptPDFUseCaseImpl implements DecryptPDFUseCase {

    @Override
    public byte[] decryptPDF(MultipartFile pdf, String password) throws PDFNotLoadedException, IOException {
        PDDocument pdfLoaded = null;
        try {
            pdfLoaded = Loader.loadPDF(pdf.getBytes(),password);
            pdfLoaded.setAllSecurityToBeRemoved(true);
            ByteArrayOutputStream pdfDecrypted = new ByteArrayOutputStream();
            pdfLoaded.save(pdfDecrypted);
            return pdfDecrypted.toByteArray();
        } catch (IOException e) {
            throw new PDFNotLoadedException("PDF could not be loaded");
        } finally{
            if(pdfLoaded!=null){
                pdfLoaded.close();
            }
            pdf.getInputStream().close();
        }
    }
}
