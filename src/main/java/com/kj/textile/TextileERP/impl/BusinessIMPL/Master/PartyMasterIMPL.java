package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.Exceptions.ResouceNotFoundException;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.CityMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyCategoryMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyMaster;
import com.kj.textile.TextileERP.model.Master.PartyMasterModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.CityMasterRepo;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.PartyCategoryMasterRepo;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.PartyMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.PartyMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyMasterIMPL implements PartyMasterService {
    @Autowired
    PartyMasterRepo partyMasterRepo;

    @Autowired
    PartyCategoryMasterRepo partyCategoryMasterRepo;

    @Autowired
    CityMasterRepo cityMasterRepo;

     PartyMasterIMPL(PartyMasterRepo partyMasterRepo,PartyCategoryMasterRepo partyCategoryMasterRepo, CityMasterRepo cityMasterRepo){
         this.partyMasterRepo =partyMasterRepo;
         this.partyCategoryMasterRepo =partyCategoryMasterRepo;
         this.cityMasterRepo =cityMasterRepo;
    }
    @Override
    public List<PartyCategoryMaster> getPartyCategory(){
        return partyCategoryMasterRepo.findAll();
    }

    @Override
    public List<PartyMaster> getAllPartyMasterList() {
        return  partyMasterRepo.findAll();
    }
    @Override
    public PartyMaster getPartyData(Long partyId) {
        Optional<PartyMaster> partyData = partyMasterRepo.findById(partyId);
        return partyData.orElse(null);
    }

    @Override
    public PartyMaster savePartyMaster(PartyMasterModel partyMasterModel) {
        PartyMaster partyMaster =  new PartyMaster();
        partyMaster.setPartyName(partyMasterModel.getPartyName());
        partyMaster.setPartAddress(partyMasterModel.getPartAddress());
        partyMaster.setPanNo(partyMasterModel.getPanNo());
        partyMaster.setMobNo(partyMasterModel.getMobNo());
        partyMaster.setPhNo(partyMasterModel.getPhNo());
        partyMaster.setEmailId(partyMasterModel.getEmailId());
        partyMaster.setGstNo(partyMasterModel.getGstNo());
        partyMaster.setBrokerId(partyMasterModel.getBrokerId());
        CityMaster cityMaster;
        if(partyMasterModel.getCityId()==null){
            cityMaster = null;
        } else {
            cityMaster =  cityMasterRepo.findByCityId(partyMasterModel.getCityId());
        }

        PartyCategoryMaster partyCategoryMaster;
        if(partyMasterModel.getPartyCategoryId()==null){
            partyCategoryMaster = null;
        } else {
            partyCategoryMaster =  partyCategoryMasterRepo.findByPartyCategoryId(partyMasterModel.getPartyCategoryId());
        }

        partyMaster.setPartyCategoryMaster(partyCategoryMaster);
        partyMaster.setCityMaster(cityMaster);
        partyMasterRepo.save(partyMaster);
        return partyMaster;
    }

    @Override
    public PartyMaster updatePartyMaster(PartyMasterModel partyMasterModel) {

        PartyMaster partyMaster =  partyMasterRepo.findByPartyId(partyMasterModel.getPartyId());

        if(partyMaster==null){
            throw new ResouceNotFoundException("Party not found with ID: " + partyMasterModel.getPartyId());
        }
        partyMaster.setPartAddress(partyMasterModel.getPartAddress());
        partyMaster.setPartyName(partyMasterModel.getPartyName());
        partyMaster.setPanNo(partyMasterModel.getPanNo());
        partyMaster.setMobNo(partyMasterModel.getMobNo());
        partyMaster.setPhNo(partyMasterModel.getPhNo());
        partyMaster.setEmailId(partyMasterModel.getEmailId());
        partyMaster.setGstNo(partyMasterModel.getGstNo());
        partyMaster.setBrokerId(partyMasterModel.getBrokerId());
        CityMaster cityMaster;
        if(partyMasterModel.getCityId()==null){
            cityMaster = null;
        } else {
            cityMaster =  cityMasterRepo.findByCityId(partyMasterModel.getCityId());
        }

        PartyCategoryMaster partyCategoryMaster;
        if(partyMasterModel.getPartyCategoryId()==null){
            partyCategoryMaster = null;
        } else {
            partyCategoryMaster =  partyCategoryMasterRepo.findByPartyCategoryId(partyMasterModel.getPartyCategoryId());
        }

        partyMaster.setPartyCategoryMaster(partyCategoryMaster);
        partyMaster.setCityMaster(cityMaster);
        partyMasterRepo.save(partyMaster);
        return partyMaster;
    }
}
