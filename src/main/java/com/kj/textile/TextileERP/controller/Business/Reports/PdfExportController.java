package com.kj.textile.TextileERP.controller.Business.Reports;

import com.kj.textile.TextileERP.services.BusinessService.Reports.PDFExportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@ResponseBody
@RestController
@RequestMapping("/api/v1/pdf")
public class PdfExportController {

    @Autowired
    PDFExportService pdfExportService;

    @GetMapping("/downloadloom")
    public void exportLoomMasterPdf(HttpServletResponse response) throws IOException {
        pdfExportService.exportLoomMasterPdf(response);
    }
}
