package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.Exceptions.ResouceNotFoundException;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.YarnMaster;
import com.kj.textile.TextileERP.enums.CountType;
import com.kj.textile.TextileERP.model.Master.YarnMasterModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.YarnMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.YarnMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountMasterIMPL implements YarnMasterService {
    @Autowired
    YarnMasterRepo yarnMasterRepo;

    public CountMasterIMPL(YarnMasterRepo yarnMasterRepo){
        this.yarnMasterRepo=yarnMasterRepo;
    }
    @Override
    public List<YarnMaster> getAllCount() {
        return yarnMasterRepo.findAll();
    }

    @Override
    public YarnMaster getCountData(Long CountId) {
        return yarnMasterRepo.findByCountId(CountId);
    }

    @Override
    public YarnMaster saveYarnMaster(YarnMasterModel yarnMasterModel) {
        YarnMaster yarnMaster = new YarnMaster();
        yarnMaster.setCountNo(yarnMasterModel.getCountNo());
        yarnMaster.setCountName(yarnMasterModel.getCountName());
        yarnMaster.setCountType(CountType.valueOf(yarnMasterModel.getCountType()));
        yarnMasterRepo.save(yarnMaster);
        return yarnMaster;
    }

    @Override
    public YarnMaster updateYarnMaster(YarnMasterModel yarnMasterModel) {
        YarnMaster yarnMaster = yarnMasterRepo.findByCountId(yarnMasterModel.getCountId());
        if(yarnMaster==null){
            throw new ResouceNotFoundException("Count not found with ID: " + yarnMasterModel.getCountId());
        }
        yarnMaster.setCountNo(yarnMasterModel.getCountNo());
        yarnMaster.setCountName(yarnMasterModel.getCountName());
        yarnMaster.setCountType(CountType.valueOf(yarnMasterModel.getCountType()));
        yarnMasterRepo.save(yarnMaster);
        return yarnMaster;
    }
}
