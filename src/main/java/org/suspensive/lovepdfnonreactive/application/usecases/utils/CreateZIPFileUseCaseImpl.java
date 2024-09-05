package org.suspensive.lovepdfnonreactive.application.usecases.utils;

import org.springframework.stereotype.Component;
import org.suspensive.lovepdfnonreactive.domain.models.dto.PDFSplitterDTO;
import org.suspensive.lovepdfnonreactive.domain.ports.input.utils.CreateZIPFileUseCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class CreateZIPFileUseCaseImpl implements CreateZIPFileUseCase {
    @Override
    public byte[] createZIP(PDFSplitterDTO pdfs) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            ZipEntry zipEntry1 = new ZipEntry("document1.pdf");
            zipOutputStream.putNextEntry(zipEntry1);
            zipOutputStream.write(pdfs.pdf1());
            zipOutputStream.closeEntry();

            ZipEntry zipEntry2 = new ZipEntry("document2.pdf");
            zipOutputStream.putNextEntry(zipEntry2);
            zipOutputStream.write(pdfs.pdf2());
            zipOutputStream.closeEntry();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
