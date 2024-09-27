package org.suspensive.lovepdfnonreactive.application.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.PDFNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.models.exceptions.UserNotFoundException;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.DeletePDFByNameUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.GetAllPDFsUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.GetPDFByNameUseCase;
import org.suspensive.lovepdfnonreactive.domain.ports.input.user.pdf.UploadPDFUseCase;

import java.io.IOException;
import java.util.Map;

@Service
public class UserPDFService {

    private final DeletePDFByNameUseCase deletePDFByNameUseCase;
    private final GetAllPDFsUseCase getAllPDFsUseCase;
    private final GetPDFByNameUseCase getPDFByNameUseCase;
    private final UploadPDFUseCase uploadPDFUseCase;

    public UserPDFService(DeletePDFByNameUseCase deletePDFByNameUseCase, GetAllPDFsUseCase getAllPDFsUseCase, GetPDFByNameUseCase getPDFByNameUseCase, UploadPDFUseCase uploadPDFUseCase) {
        this.deletePDFByNameUseCase = deletePDFByNameUseCase;
        this.getAllPDFsUseCase = getAllPDFsUseCase;
        this.getPDFByNameUseCase = getPDFByNameUseCase;
        this.uploadPDFUseCase = uploadPDFUseCase;
    }

    public Map<String,byte[]> getAllPDFs() throws UserNotFoundException {
        return getAllPDFsUseCase.getAllPDFs(getPrincipalName());
    }

    public byte[] getPDFByName(String pdfName) throws UserNotFoundException, PDFNotFoundException {
        byte[] pdf = getPDFByNameUseCase.getPDFByName(getPrincipalName(), pdfName);
        if (pdf == null) {
            throw new PDFNotFoundException("PDF with name " + pdfName + " not found");
        }
        return pdf;
    }

    public void uploadPDF(MultipartFile pdfFile) throws UserNotFoundException, IOException {
        boolean status = uploadPDFUseCase.uploadPDF(getPrincipalName(), pdfFile);
        if (!status) {
            throw new IOException("Error uploading PDF, pdf with same name already exists");
        }
    }

    public void deletePDFByName(String pdfName) throws UserNotFoundException, PDFNotFoundException {
        boolean status = deletePDFByNameUseCase.deletePDFByName(getPrincipalName(), pdfName);
        if (!status) {
            throw new PDFNotFoundException("PDF with name " + pdfName + " not found");
        }
    }


    private String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
