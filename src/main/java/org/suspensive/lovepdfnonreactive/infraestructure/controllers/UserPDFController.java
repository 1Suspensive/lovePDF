package org.suspensive.lovepdfnonreactive.infraestructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.application.services.UserPDFService;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController("/user/pdfs")
public class UserPDFController {
    private final UserPDFService userPDFService;

    public UserPDFController(UserPDFService userPDFService) {
        this.userPDFService = userPDFService;
    }

    @GetMapping
    public ResponseEntity<Map<String,byte[]>> getAllPDFs() throws UserNotFoundException {
        return new ResponseEntity<>(userPDFService.getAllPDFs(), HttpStatus.OK);
    }

    @GetMapping("/{pdfName}")
    public ResponseEntity<byte[]> getPDFByName(@PathVariable("pdfName") String pdfName) throws UserNotFoundException, PDFNotFoundException {
        return new ResponseEntity<>(userPDFService.getPDFByName(pdfName),HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadPDF(@RequestParam("pdf") MultipartFile pdfFile) throws UserNotFoundException, IOException {
        userPDFService.uploadPDF(pdfFile);
        return new ResponseEntity<>(Collections.singletonMap("message","PDF uploaded successfully"),HttpStatus.CREATED);
    }

    @DeleteMapping("/{pdfName}")
    public ResponseEntity<Map<String,String>> deletePDFByName(@PathVariable("pdfName") String pdfName) throws UserNotFoundException, PDFNotFoundException {
        userPDFService.deletePDFByName(pdfName);
        return new ResponseEntity<>(Collections.singletonMap("message","PDF deleted successfully"),HttpStatus.OK);
    }

}
