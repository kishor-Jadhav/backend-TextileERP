package com.kj.textile.TextileERP.services.BusinessService.Reports;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import com.kj.textile.TextileERP.services.BusinessService.Master.LoomMasterService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class PDFExportService {
    @Autowired
    LoomMasterService loomMasterService;
    public void exportLoomMasterPdf(HttpServletResponse response) throws IOException {
        List<LoomMaster> loomData = loomMasterService.getLoomMasterList(); // Ensure this method exists!

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=loom-master.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Loom Master Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        document.add(new Paragraph("Generated on: " + new Date()));
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 3, 3});

        addTableHeader(table, "ID");
        addTableHeader(table, "Loom No");
        addTableHeader(table, "Shift Name");

        for (LoomMaster loom : loomData) {
            table.addCell(String.valueOf(loom.getLoomMasterId()));
            table.addCell(loom.getLoomNo());
            table.addCell(loom.getShiftMaster().getShiftName());
        }

        addTableHeader(table, "");
        addTableHeader(table, "Total");
        addTableHeader(table, "100");

        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setBorderWidth(1);
        header.setPhrase(new Phrase(headerTitle, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        table.addCell(header);
    }


}
