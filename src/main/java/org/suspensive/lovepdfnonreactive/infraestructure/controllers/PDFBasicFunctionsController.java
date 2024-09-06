package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.application.services.PDFService;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@RestController
public class PDFBasicFunctionsController {

    private final PDFService pdfService;

    public PDFBasicFunctionsController(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping(value = "/merge", consumes = {"multipart/form-data"}, produces = "application/pdf")
    public byte[] mergePDFs(@RequestParam("pdf1") MultipartFile pdf1,
                            @RequestParam("pdf2") MultipartFile pdf2) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFMergerException {
        // Exception to handle when the file is deleted from tomcat server
        try {
            return pdfService.mergePDFs(pdf1, pdf2);
        } catch (NoSuchFileException e) {
            return null;
        }
    }

    @PostMapping(value = "/split/{pageToSplit}", consumes = {"multipart/form-data"}, produces = "application/zip")
    public byte[] splitPDF(@RequestParam("pdf") MultipartFile pdf, @PathVariable int pageToSplit) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFSplitterException {
        return pdfService.splitPDF(pdf, pageToSplit);
    }

    @PostMapping(value = "/removePages", consumes = {"multipart/form-data"}, produces = "application/pdf")
    public byte[] revomePages(@RequestParam("pdf") MultipartFile pdf, @RequestParam("pages") int[] pages) throws PDFRemovePagesException, MaximumSizeExceededException, IOException, PDFNotLoadedException {
        return pdfService.removePDFPages(pdf, pages);
    }

    public byte[] encryptPDF(@RequestParam("pdf") MultipartFile pdf, @RequestParam("password") String password) throws MaximumSizeExceededException, IOException, PDFNotLoadedException {
        return pdfService.encryptPDF(pdf, password);
    }

    public byte[] decryptPDF(@RequestParam("pdf") MultipartFile pdf, @RequestParam("password") String password) throws IOException, PDFNotLoadedException {
        return pdfService.decryptPDF(pdf, password);
    }

    public byte[] signPDF(@RequestParam("pdf") MultipartFile pdf, @RequestParam("signature") MultipartFile signature){
        return null;
    }

}