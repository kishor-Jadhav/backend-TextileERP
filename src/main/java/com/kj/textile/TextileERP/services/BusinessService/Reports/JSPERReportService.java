package com.kj.textile.TextileERP.services.BusinessService.Reports;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterDetails;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterMain;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import com.kj.textile.TextileERP.model.Master.DesignMasterMainModel;
import com.kj.textile.TextileERP.model.Master.LoomMasterModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.LoomMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.DesignMasterDetailService;
import com.kj.textile.TextileERP.services.BusinessService.Master.DesignMasterMainService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JSPERReportService {
    @Autowired
    LoomMasterRepo   loomMasterRepo;
    @Autowired
    DesignMasterMainService designMasterMainService;

    @Autowired
    DesignMasterDetailService designMasterDetailService;

    public byte[] exportLoomMasterReportToPdf() throws Exception {
        // Fetch data from DB
        List<LoomMaster> data = loomMasterRepo.findAll();
        List<LoomMasterModel> loomMasterModelList = new ArrayList<>();
        for(LoomMaster loom:data){
            LoomMasterModel loomMasterModel = new LoomMasterModel();
            loomMasterModel.setLoomNo(loom.getLoomNo());
            loomMasterModel.setShiftName(loom.getShiftMaster().getShiftName());
            loomMasterModel.setLoomMasterDetailId(loom.getLoomMasterDetailId());
            loomMasterModel.setUnitId(loom.getUnitId());
            loomMasterModelList.add(loomMasterModel);
        }
        try (InputStream reportStream = getClass().getResourceAsStream("/reports/loom_report.jrxml")) {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(loomMasterModelList); // your data
            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to export PDF", e);
        }
    }

    public byte[] exportQualityreport() throws Exception {
        // Fetch data from DB
        //List<DesignMasterMain> data = designMasterMainService.findAllByOrderByQualityMaster_QualityNameAsc();
        List<DesignMasterMain> data = designMasterMainService.findAllByOrderByDesignNameAsc();
        List<DesignMasterMainModel> designMasterMainModelList = new ArrayList<>();
        for(DesignMasterMain designMasterMain:data){
            DesignMasterMainModel designMasterMainModel = new DesignMasterMainModel();
            designMasterMainModel.setDesignMasterMainId(designMasterMain.getDesignMasterMainId());
            designMasterMainModel.setQualMaster(designMasterMain.getQualityMaster());
            designMasterMainModel.setDesignName(designMasterMain.getDesignName());
            designMasterMainModel.setPick(designMasterMain.getPick());
            designMasterMainModel.setWarp(designMasterMain.getWarp());
            designMasterMainModel.setWeft(designMasterMain.getWeft());
            designMasterMainModel.setWeftWestage(designMasterMain.getWeftWestage());
            designMasterMainModel.setReed(designMasterMain.getReed());
            designMasterMainModel.setWidth(designMasterMain.getWidth());
            designMasterMainModel.setReedSpace(designMasterMain.getReedSpace());
            List<DesignMasterDetails>  designMasterDetails = designMasterDetailService.getDataById(designMasterMain.getDesignMasterMainId());
            designMasterMainModel.setDesignMasterDetailsModel(designMasterDetailService.getDataFromEntity(designMasterDetails));
            System.out.println(designMasterMainModel);
            designMasterMainModelList.add(designMasterMainModel);
        }
        try (InputStream reportStream = getClass().getResourceAsStream("/reports/quality_report.jrxml")) {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(designMasterMainModelList); // your data
            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to export PDF", e);
        }
    }

    public byte[] exportDesignMasterReportToPdf() throws Exception {
        // Fetch data from DB
        //List<DesignMasterMain> data = designMasterMainService.findAllByOrderByQualityMaster_QualityNameAsc();
        List<DesignMasterMain> data = designMasterMainService.findAllByOrderByDesignNameAsc();
        List<DesignMasterMainModel> designMasterMainModelList = new ArrayList<>();
        for(DesignMasterMain designMasterMain:data){
            DesignMasterMainModel designMasterMainModel = new DesignMasterMainModel();
            designMasterMainModel.setDesignMasterMainId(designMasterMain.getDesignMasterMainId());
            designMasterMainModel.setQualMaster(designMasterMain.getQualityMaster());
            designMasterMainModel.setDesignName(designMasterMain.getDesignName());
            designMasterMainModel.setPick(designMasterMain.getPick());
            designMasterMainModel.setWarp(designMasterMain.getWarp());
            designMasterMainModel.setWeft(designMasterMain.getWeft());
            designMasterMainModel.setWeftWestage(designMasterMain.getWeftWestage());
            designMasterMainModel.setReed(designMasterMain.getReed());
            designMasterMainModel.setWidth(designMasterMain.getWidth());
            designMasterMainModel.setReedSpace(designMasterMain.getReedSpace());
            List<DesignMasterDetails>  designMasterDetails = designMasterDetailService.getDataById(designMasterMain.getDesignMasterMainId());
            designMasterMainModel.setDesignMasterDetailsModel(designMasterDetailService.getDataFromEntity(designMasterDetails));
            System.out.println(designMasterMainModel);
            designMasterMainModelList.add(designMasterMainModel);
        }
        try (InputStream mainStream = getClass().getResourceAsStream("/reports/design_main_detail_report.jrxml")) {
            // 1️⃣ Compile the main report
            JasperReport jasperReport = JasperCompileManager.compileReport(mainStream);

            // 2️⃣ Compile the subreport
            InputStream subStream = getClass().getResourceAsStream("/reports/design_detail_report.jrxml");
            JasperReport subJasperReport = JasperCompileManager.compileReport(subStream);

            // 3️⃣ Prepare data source and parameters
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(designMasterMainModelList);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("SUBREPORT", subJasperReport); // pass the compiled subreport

            // 4️⃣ Fill and export
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to export PDF", e);
        }
    }
}
