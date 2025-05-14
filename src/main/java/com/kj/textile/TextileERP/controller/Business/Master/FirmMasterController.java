package com.kj.textile.TextileERP.controller.Business.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.FirmMaster;
import com.kj.textile.TextileERP.model.Master.CityMasterModel;
import com.kj.textile.TextileERP.model.Master.FirmMasterModel;
import com.kj.textile.TextileERP.services.BusinessService.Master.FirmMasaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/")
public class FirmMasterController {
    @Autowired
    FirmMasaterService firmMasaterService;

    @GetMapping("getfirm")
    public List<FirmMaster> getAllFirmData() {
        List<FirmMaster> firmData = firmMasaterService.getAllFirmMasterList();
        return firmData;
    }
    @GetMapping("getfirmData/{firmId}")
    public FirmMaster getFirmData(@PathVariable String firmId) {
        FirmMaster firmData = firmMasaterService.getFirmData(Long.parseLong(firmId));
        return firmData;
    }
    @PostMapping("savefirmmaster")
    public FirmMaster saveFirmMaster(@RequestBody FirmMasterModel firmMasterModel){
        return firmMasaterService.saveFirmMaster(firmMasterModel);
    }
    @PostMapping("updatefirmmaster")
    public FirmMaster updateFirmMaster(@RequestBody FirmMasterModel firmMasterModel){
        return firmMasaterService.updateFirmMaster(firmMasterModel);
    }
}
