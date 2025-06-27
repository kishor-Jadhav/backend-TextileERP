package com.kj.textile.TextileERP.controller.Business.Reports;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import com.kj.textile.TextileERP.services.BusinessService.Master.LoomMasterService;
import com.kj.textile.TextileERP.services.BusinessService.Reports.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/excel")
//@RequestMapping("/excel")
public class ExcelExportController {
    @Autowired
    LoomMasterService loomMasterService;

    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/downloadloom")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        List<LoomMaster> loom = loomMasterService.getLoomMasterList();
        ByteArrayInputStream in = excelExportService.exportLoomData(loom);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");



        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
