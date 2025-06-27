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
public class MasterReportController {

    @Autowired
    private JSPERReportService jasperReportService;

    @GetMapping("/downloaddesign")
    public void designMasterreport(HttpServletResponse response) throws Exception {
        byte[] pdfData = jasperReportService.exportDesignMasterReportToPdf();

        // Set headers and write response
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=design-report.pdf");
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }
    @GetMapping("/downloadqualitydetailreport")
    public void QualityDetailreport(HttpServletResponse response) throws Exception {
        byte[] pdfData = jasperReportService.exportQualityreport();

        // Set headers and write response
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=quality-report.pdf");
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }
}
