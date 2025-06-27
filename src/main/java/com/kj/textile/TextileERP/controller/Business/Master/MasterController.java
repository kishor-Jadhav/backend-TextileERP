package com.kj.textile.TextileERP.controller.Business.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterMain;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.QualityMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.YarnMaster;
import com.kj.textile.TextileERP.model.Master.DesignMasterMainModel;
import com.kj.textile.TextileERP.model.Master.QualityMasterModel;
import com.kj.textile.TextileERP.model.Master.YarnMasterModel;
import com.kj.textile.TextileERP.services.BusinessService.Master.DesignMasterDetailService;
import com.kj.textile.TextileERP.services.BusinessService.Master.DesignMasterMainService;
import com.kj.textile.TextileERP.services.BusinessService.Master.QualityMasterService;
import com.kj.textile.TextileERP.services.BusinessService.Master.YarnMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/")
public class MasterController {
    @Autowired
    YarnMasterService yarnMasterService;
    @Autowired
    DesignMasterDetailService designMasterDetailService;
    @Autowired
    DesignMasterMainService designMasterMainService;
    @Autowired
    QualityMasterService qualityMasterService;

    @GetMapping("getCountList")
    public List<YarnMaster> getCountList(){
        return yarnMasterService.getAllCount();
    }
    @GetMapping("getQualityList")
    public List<QualityMaster> getQualityList(){
        return qualityMasterService.getAllQuality();
    }
    @GetMapping("getDesignList")
    public List<DesignMasterMain> getDesignList(){
        return designMasterMainService.getAllRecord();
    }

    @GetMapping("getCount/{countId}")
    public YarnMaster getCount(@PathVariable String countId){
        return yarnMasterService.getCountData(Long.parseLong(countId));
    }

    @GetMapping("getQuality/{qualityId}")
    public QualityMaster getQuality(@PathVariable String qualityId){
        return qualityMasterService.getQualityData(Long.parseLong(qualityId));
    }

    @GetMapping("getdesign/{designId}")
    public DesignMasterMainModel getDesign(@PathVariable String designId){
        return designMasterMainService.getDataById(Long.parseLong(designId));
    }

    @PostMapping("saveCount")
    public YarnMaster saveCount(@RequestBody YarnMasterModel yarnMasterModel){
        return yarnMasterService.saveYarnMaster(yarnMasterModel);
    }

    @PostMapping("savequality")
    public QualityMaster saveQuality(@RequestBody QualityMasterModel qualityMasterModel){
        return qualityMasterService.saveQualityMaster(qualityMasterModel);
    }
    @PostMapping("savedesign")
    public DesignMasterMain saveDesign(@RequestBody DesignMasterMainModel designMasterMainModel){
        return designMasterMainService.saveForm(designMasterMainModel);
    }
    @PostMapping("updateCount")
    public YarnMaster updateCount(@RequestBody YarnMasterModel yarnMasterModel){
        return yarnMasterService.updateYarnMaster(yarnMasterModel);
    }

    @PostMapping("updatequality")
    public QualityMaster updateQuality(@RequestBody QualityMasterModel qualityMasterModel){
        return qualityMasterService.updateQualityMaster(qualityMasterModel);
    }
    @PostMapping("updatedesign")
    public DesignMasterMain updateDesign(@RequestBody DesignMasterMainModel designMasterMainModel){
        return designMasterMainService.updateForm(designMasterMainModel);
    }
}
