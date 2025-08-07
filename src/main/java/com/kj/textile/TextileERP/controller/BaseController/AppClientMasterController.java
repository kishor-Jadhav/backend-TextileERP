package com.kj.textile.TextileERP.controller.BaseController;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientMasterModel;
import com.kj.textile.TextileERP.model.Master.CityMasterModel;
import com.kj.textile.TextileERP.services.BaseService.AppClientMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@ResponseBody
@RestController
@RequestMapping("/api/v2/auth/")
public class AppClientMasterController {
    @Autowired
    AppClientMasterService appClientMasterService;

    @GetMapping("getclientlist")
    public List<AppClientMaster> getAllData() {

        List<AppClientMaster> dataList = appClientMasterService.getAllList();
        return dataList;
    }
    @GetMapping("getclient/{Id}")
    public AppClientMaster getData(@PathVariable String Id) {
        AppClientMaster data = appClientMasterService.getDataById(Long.parseLong(Id));
        return data;
    }
    @PostMapping("saveappclient")
    public AppClientMaster saveForm(@RequestBody AppClientMasterModel requestModel){
        return appClientMasterService.saveForm(requestModel);
    }
    @PostMapping("updateappclient")
    public AppClientMaster updateForm(@RequestBody AppClientMasterModel requestModel){
        return appClientMasterService.updateForm(requestModel);
    }
}
