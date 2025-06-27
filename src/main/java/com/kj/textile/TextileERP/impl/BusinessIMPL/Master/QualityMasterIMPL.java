package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.Exceptions.ResouceNotFoundException;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.QualityMaster;
import com.kj.textile.TextileERP.model.Master.QualityMasterModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.QualityMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.QualityMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualityMasterIMPL implements QualityMasterService {
@Autowired
    QualityMasterRepo qualityMasterRepo;
    public QualityMasterIMPL(QualityMasterRepo qualityMasterRepo){
        this.qualityMasterRepo = qualityMasterRepo;
    }
    @Override
    public List<QualityMaster> getAllQuality() {
        return qualityMasterRepo.findAll();
    }

    @Override
    public QualityMaster getQualityData(Long Id) {
        return qualityMasterRepo.findByQualityId(Id);
    }

    @Override
    public QualityMaster saveQualityMaster(QualityMasterModel qualityMasterModel) {
        QualityMaster qualityMaster = new  QualityMaster();
        qualityMaster.setQualityAlice(qualityMasterModel.getQualityAlice());
        qualityMaster.setQualityName(qualityMasterModel.getQualityAlice());
        qualityMaster.setReed(qualityMasterModel.getReed());
        qualityMaster.setWarp(qualityMasterModel.getWarp());
        qualityMaster.setWeft(qualityMasterModel.getWeft());
        qualityMaster.setPick(qualityMasterModel.getPick());
        qualityMaster.setWidth(qualityMasterModel.getWidth());
        qualityMaster.setReedSpace(qualityMasterModel.getReedSpace());
        qualityMasterRepo.save(qualityMaster);
        return qualityMaster;
    }

    @Override
    public QualityMaster updateQualityMaster(QualityMasterModel qualityMasterModel) {
        QualityMaster qualityMaster = qualityMasterRepo.findByQualityId(qualityMasterModel.getQualityId());
         if(qualityMaster== null){
             throw new ResouceNotFoundException("Quality Not Found for NAme-" + qualityMasterModel.getQualityName());
         }
        qualityMaster.setQualityAlice(qualityMasterModel.getQualityAlice());
        qualityMaster.setQualityName(qualityMasterModel.getQualityAlice());
        qualityMaster.setReed(qualityMasterModel.getReed());
        qualityMaster.setWarp(qualityMasterModel.getWarp());
        qualityMaster.setWeft(qualityMasterModel.getWeft());
        qualityMaster.setPick(qualityMasterModel.getPick());
        qualityMaster.setWidth(qualityMasterModel.getWidth());
        qualityMaster.setReedSpace(qualityMasterModel.getReedSpace());
        qualityMasterRepo.save(qualityMaster);
        return qualityMaster;
    }
}
