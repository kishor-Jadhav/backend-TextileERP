package com.kj.textile.TextileERP.controller.Business.Reports;

import com.kj.textile.TextileERP.services.BusinessService.Reports.JSPERReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
@RequestMapping("/api/v1/jsper")
public class JasperReportController {

    @Autowired
    private JSPERReportService jasperReportService;

    @GetMapping("/downloadloom")
    public void downloadPdf(HttpServletResponse response) throws Exception {
        byte[] pdfData = jasperReportService.exportLoomMasterReportToPdf();

        // Set headers and write response
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=loom-report.pdf");
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }
}
