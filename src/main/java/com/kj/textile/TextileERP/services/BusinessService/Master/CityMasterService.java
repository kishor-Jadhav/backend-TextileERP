package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;

import com.kj.textile.TextileERP.model.Master.CityMasterModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityMasterService {
    List<CityMaster> getAllCityList();

    CityMaster saveCityMaster(CityMasterModel cityMasterModel);
    CityMaster updateCityMaster(CityMasterModel cityMasterModel);
    CityMaster getCityData(Long cityId);
}
