package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.Exceptions.ResouceNotFoundException;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.model.Master.CityMasterModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.CityMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.CityMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CityMasterIMPL implements CityMasterService {

    @Autowired
    CityMasterRepo cityMasterRepo;
public CityMasterIMPL( CityMasterRepo cityMasterRepo){
    this.cityMasterRepo =cityMasterRepo;
}

    @Override
    public List<CityMaster> getAllCityList() {
        List<CityMaster> cityData = cityMasterRepo.findAll();
        return  cityData;
    }
    @Override
    public CityMaster getCityData(Long cityId) {
        Optional<CityMaster> cityData = cityMasterRepo.findById(cityId);
        return cityData.orElse(null);
    }
    @Override
    public CityMaster saveCityMaster(CityMasterModel cityMasterModel) {
        UserContextDTO user = UserContext.get();
        CityMaster cityMaster =  new CityMaster();
        cityMaster.setCityName(cityMasterModel.getCityName());
        cityMaster.setState(cityMasterModel.getState());
        cityMaster.setPinCode(cityMasterModel.getPinCode());
        cityMaster.getUserAuditEntity().setCreatedDate(LocalDateTime.now());
        cityMaster.getUserAuditEntity().setCreatedBy(user.getUsername());
        cityMasterRepo.save(cityMaster);
        return cityMaster;
    }

    @Override
    public CityMaster updateCityMaster(CityMasterModel cityMasterModel) {
        UserContextDTO user = UserContext.get();
        CityMaster cityMaster =  cityMasterRepo.findByCityId(cityMasterModel.getCityId());

        if(cityMaster==null){
            throw new ResouceNotFoundException("City not found with ID: " + cityMasterModel.getCityId());
        }
        cityMaster.setCityName(cityMasterModel.getCityName());
        cityMaster.setState(cityMasterModel.getState());
        cityMaster.setPinCode(cityMasterModel.getPinCode());
        cityMaster.getUserAuditEntity().setUpdatedBy(user.getUsername());
        cityMaster.getUserAuditEntity().setUpdatedDate(LocalDateTime.now());
        cityMasterRepo.save(cityMaster);
        return cityMaster;
    }

}
