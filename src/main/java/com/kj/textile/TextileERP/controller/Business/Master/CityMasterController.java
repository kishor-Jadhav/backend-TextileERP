package com.kj.textile.TextileERP.controller.Business.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.model.Master.CityMasterModel;
import com.kj.textile.TextileERP.services.BusinessService.Master.CityMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/")
public class CityMasterController {

    @Autowired
    CityMasterService cityMasterService;

    @GetMapping("getcity")
    public List<CityMaster> getAllCityData() {
        List<CityMaster> cityData = cityMasterService.getAllCityList();
        return cityData;
    }
    @GetMapping("getcityData/{cityId}")
    public CityMaster getCityData(@PathVariable String cityId) {
        CityMaster cityData = cityMasterService.getCityData(Long.parseLong(cityId));
        return cityData;
    }
    @PostMapping("savecitymaster")
    public CityMaster saveCityMaster(@RequestBody CityMasterModel cityMasterModel){
       return cityMasterService.saveCityMaster(cityMasterModel);
    }
    @PostMapping("updatecitymaster")
    public CityMaster updateCityMaster(@RequestBody CityMasterModel cityMasterModel){
        return cityMasterService.updateCityMaster(cityMasterModel);
    }
}
