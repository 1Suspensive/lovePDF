package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.application.services.PDFServiceBasicFunctionsService;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@RestController
public class PDFBasicFunctionsController {

    private final PDFServiceBasicFunctionsService pdfServiceBasicFunctionsService;

    public PDFBasicFunctionsController(PDFServiceBasicFunctionsService pdfServiceBasicFunctionsService) {
        this.pdfServiceBasicFunctionsService = pdfServiceBasicFunctionsService;
    }

    @PostMapping(value = "/merge", consumes = {"multipart/form-data"}, produces = "application/pdf")
    public byte[] mergePDFs(@RequestParam("pdf1") MultipartFile pdf1,
                            @RequestParam("pdf2") MultipartFile pdf2) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFMergerException {
        // Exception to handle when the file is deleted from tomcat server
        try {
            return pdfServiceBasicFunctionsService.mergePDFs(pdf1, pdf2);
        } catch (NoSuchFileException e) {
            return null;
        }
    }

    @PostMapping(value = "/split/{pageToSplit}", consumes = {"multipart/form-data"}, produces = "application/zip")
    public byte[] splitPDF(@RequestParam("pdf") MultipartFile pdf, @PathVariable int pageToSplit) throws MaximumSizeExceededException, IOException, PDFNotLoadedException, PDFSplitterException {
        return pdfServiceBasicFunctionsService.splitPDF(pdf, pageToSplit);
    }

    @PostMapping(value = "/removePages", consumes = {"multipart/form-data"}, produces = "application/pdf")
    public byte[] removePages(@RequestParam("pdf") MultipartFile pdf, @RequestParam("pages") int[] pages) throws PDFRemovePagesException, MaximumSizeExceededException, IOException, PDFNotLoadedException {
        return pdfServiceBasicFunctionsService.removePDFPages(pdf, pages);
    }

    @PostMapping(value = "/encrypt", consumes = {"multipart/form-data"}, produces = "application/pdf")
    public byte[] encryptPDF(@RequestParam("pdf") MultipartFile pdf, @RequestParam("password") String password) throws MaximumSizeExceededException, IOException, PDFNotLoadedException {
        return pdfServiceBasicFunctionsService.encryptPDF(pdf, password);
    }

    @PostMapping(value = "/decrypt", consumes = {"multipart/form-data"}, produces = "application/pdf")
    public byte[] decryptPDF(@RequestParam("pdf") MultipartFile pdf, @RequestParam("password") String password) throws IOException, PDFNotLoadedException {
        return pdfServiceBasicFunctionsService.decryptPDF(pdf, password);
    }

}