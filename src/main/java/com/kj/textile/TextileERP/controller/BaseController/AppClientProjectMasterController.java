package com.kj.textile.TextileERP.controller.BaseController;

import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientProjectMasterModel;
import com.kj.textile.TextileERP.services.BaseService.AppClientProjectMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@ResponseBody
@RestController
@RequestMapping("/api/v2/auth/")
public class AppClientProjectMasterController {
    @Autowired
    AppClientProjectMasterService appClientProjectMasterService;

    @GetMapping("getclientprojlist")
    public List<AppClientProjectMaster> getAllData() {

        List<AppClientProjectMaster> dataList = appClientProjectMasterService.getAllList();
        return dataList;
    }
    @GetMapping("getclientproj/{Id}")
    public AppClientProjectMaster getData(@PathVariable String Id) {
        AppClientProjectMaster data = appClientProjectMasterService.getDataById(Long.parseLong(Id));
        return data;
    }

    @GetMapping("getclientprojbyClientname/{Id}")
    public List<AppClientProjectMaster> getclientprojbyClientname(@PathVariable String Id) {
        List<AppClientProjectMaster> data = appClientProjectMasterService.fillProjectListByClientId(Long.parseLong(Id));
        return data;
    }
    @PostMapping("saveappclientproj")
    public AppClientProjectMasterModel saveForm(@RequestBody AppClientProjectMasterModel requestModel){
        return appClientProjectMasterService.saveForm(requestModel);
    }

    @PostMapping("updateappclientproj")
    public AppClientProjectMasterModel updateForm(@RequestBody AppClientProjectMasterModel requestModel){
        return appClientProjectMasterService.updateForm(requestModel);
    }
}
