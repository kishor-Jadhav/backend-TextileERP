package com.kj.textile.TextileERP.services.BusinessService.Reports;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {
    public ByteArrayInputStream exportLoomData(List<LoomMaster> loomData) throws IOException {
        String[] columns = {"ID", "Name", "Department"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Employees");

            // Header Row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Data Rows
            int rowIdx = 1;
            for (LoomMaster loom : loomData) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(loom.getLoomMasterId());
                row.createCell(1).setCellValue(loom.getLoomNo());
                row.createCell(2).setCellValue(loom.getShiftMaster().getShiftName());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
