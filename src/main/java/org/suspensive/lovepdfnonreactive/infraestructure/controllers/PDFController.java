package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.application.services.PDFService;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.MaximumSizeExceededException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFMergerException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotLoadedException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@RestController
public class PDFController {

    private final PDFService pdfService;

    public PDFController(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping(value = "/merge",consumes = {"multipart/form-data"}, produces = "application/pdf")
    public byte[] mergePDFs(@RequestParam("pdf1") MultipartFile pdf1,
                            @RequestParam("pdf2") MultipartFile pdf2) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFMergerException {
        //Exception to handle when the file is deleted from tomcat server
        try {
            return pdfService.mergePDFs(pdf1, pdf2);
        }catch (NoSuchFileException e){
            return null;
        }
    }

}
