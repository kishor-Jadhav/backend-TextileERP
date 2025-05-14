package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.Exceptions.ResouceNotFoundException;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.FirmMaster;
import com.kj.textile.TextileERP.model.Master.CityMasterModel;
import com.kj.textile.TextileERP.model.Master.FirmMasterModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.CityMasterRepo;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.FirmMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.FirmMasaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FirmMasterIMPL implements FirmMasaterService {

    @Autowired
    FirmMasterRepo firmMasterRepo;

    @Autowired
    CityMasterRepo cityMasterRepo;
    public FirmMasterIMPL( FirmMasterRepo firmMasterRepo,CityMasterRepo cityMasterRepo){
        this.firmMasterRepo =firmMasterRepo;
        this.cityMasterRepo =cityMasterRepo;
    }

    @Override
    public List<FirmMaster> getAllFirmMasterList() {
        List<FirmMaster> FirmData = firmMasterRepo.findAll();
        return  FirmData;
    }

    @Override
    public FirmMaster saveFirmMaster(FirmMasterModel firmMasterModel) {
        FirmMaster firmMaster =  new FirmMaster();
        firmMaster.setFirmName(firmMasterModel.getFirmName());
        firmMaster.setAddress(firmMasterModel.getAddress());
        firmMaster.setAccountNo(firmMasterModel.getAccountNo());
        firmMaster.setBankName(firmMasterModel.getBankName());
        firmMaster.setBankAddress(firmMasterModel.getBankAddress());
        firmMaster.setPanNo(firmMasterModel.getPanNo());
        CityMaster cityMaster;
        if(firmMasterModel.getCityId()==null){
            cityMaster = null;
        } else {
            cityMaster =  cityMasterRepo.findByCityId(firmMasterModel.getCityId());
        }


        firmMaster.setCityMaster(cityMaster);
        firmMasterRepo.save(firmMaster);
        return firmMaster;
    }

    @Override
    public FirmMaster updateFirmMaster(FirmMasterModel firmMasterModel) {
        FirmMaster firmMaster =  firmMasterRepo.findByFirmId(firmMasterModel.getFirmId());

        if(firmMaster==null){
            throw new ResouceNotFoundException("Firm not found with ID: " + firmMasterModel.getFirmId());
        }
        firmMaster.setFirmName(firmMasterModel.getFirmName());
        firmMaster.setAddress(firmMasterModel.getAddress());
        firmMaster.setAccountNo(firmMasterModel.getAccountNo());
        firmMaster.setBankAddress(firmMasterModel.getBankAddress());
        firmMaster.setPanNo(firmMasterModel.getPanNo());

        CityMaster cityMaster =  cityMasterRepo.findByCityId(firmMasterModel.getCityId());

        firmMaster.setCityMaster(cityMaster);
        firmMasterRepo.save(firmMaster);
        return firmMaster;
    }

    @Override
    public FirmMaster getFirmData(Long firmId) {
        Optional<FirmMaster> firmData = firmMasterRepo.findById(firmId);
        return firmData.orElse(null);
    }
}
