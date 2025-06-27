package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterDetails;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterMain;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.QualityMaster;
import com.kj.textile.TextileERP.model.Master.DesignMasterDetailsModel;
import com.kj.textile.TextileERP.model.Master.DesignMasterMainModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.DesignMasterMainRepo;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.QualityMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.DesignMasterDetailService;
import com.kj.textile.TextileERP.services.BusinessService.Master.DesignMasterMainService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DesignMasterMainIMPL implements DesignMasterMainService {
  @Autowired
  DesignMasterMainRepo designMasterMainRepo;

  @Autowired
    QualityMasterRepo qualityMasterRepo;

  @Autowired
  DesignMasterDetailService designMasterDetailService;
   public DesignMasterMainIMPL(DesignMasterMainRepo designMasterMainRepo,QualityMasterRepo qualityMasterRepo){
       this.designMasterMainRepo =designMasterMainRepo;
       this.qualityMasterRepo = qualityMasterRepo;

   }
    @Override
    public List<DesignMasterMain> getAllRecord() {
        return designMasterMainRepo.findAll();
    }

    @Override
    public DesignMasterMain saveForm(DesignMasterMainModel designMasterMainModel) {
        QualityMaster qualMaster = qualityMasterRepo.findById(designMasterMainModel.getQualMaster().getQualityId())
                .orElseThrow(() -> new RuntimeException("Quality not found"));

        DesignMasterMain designMasterMain = new DesignMasterMain();
        designMasterMain.setQualityMaster(qualMaster);
        designMasterMain.setDesignName(designMasterMainModel.getDesignName());
        designMasterMain.setPick(designMasterMainModel.getPick());
        designMasterMain.setWarp(designMasterMainModel.getWarp());
        designMasterMain.setWeft(designMasterMainModel.getWeft());
        designMasterMain.setWeftWestage(designMasterMainModel.getWeftWestage());
        designMasterMain.setReed(designMasterMainModel.getReed());
        designMasterMain.setWidth(designMasterMainModel.getWidth());
        designMasterMain.setReedSpace(designMasterMainModel.getReedSpace());
        designMasterMainRepo.save(designMasterMain);
        Long Id = designMasterMainRepo.findMaxId();
        List<DesignMasterDetailsModel> designMasterDetailsModel = designMasterMainModel.getDesignMasterDetailsModel();

        designMasterDetailService.saveForm(designMasterDetailsModel,Id);
        return designMasterMain;
    }

    @Override
    public DesignMasterMain updateForm(DesignMasterMainModel designMasterMainModel) {
       DesignMasterMain designMasterMain = designMasterMainRepo.findByDesignMasterMainId(designMasterMainModel.getDesignMasterMainId());
        QualityMaster qualMaster = qualityMasterRepo.findById(designMasterMainModel.getQualMaster().getQualityId())
                .orElseThrow(() -> new RuntimeException("Quality not found"));


        designMasterMain.setQualityMaster(qualMaster);
        designMasterMain.setDesignName(designMasterMainModel.getDesignName());
        designMasterMain.setPick(designMasterMainModel.getPick());
        designMasterMain.setWarp(designMasterMainModel.getWarp());
        designMasterMain.setWeft(designMasterMainModel.getWeft());
        designMasterMain.setWeftWestage(designMasterMainModel.getWeftWestage());
        designMasterMain.setReed(designMasterMainModel.getReed());
        designMasterMain.setWidth(designMasterMainModel.getWidth());
        designMasterMain.setReedSpace(designMasterMainModel.getReedSpace());
        designMasterMainRepo.save(designMasterMain);
        List<DesignMasterDetailsModel> designMasterDetailsModel = designMasterMainModel.getDesignMasterDetailsModel();

        designMasterDetailService.saveForm(designMasterDetailsModel,designMasterMainModel.getDesignMasterMainId());

        return designMasterMain;
    }

    @Override
    public DesignMasterMainModel getDataById(Long Id) {
        DesignMasterMain designMasterMain = designMasterMainRepo.findByDesignMasterMainId(Id);
        DesignMasterMainModel designMasterMainModel = new  DesignMasterMainModel();
        designMasterMainModel.setDesignMasterMainId(designMasterMain.getDesignMasterMainId());
        designMasterMainModel.setQualMaster(designMasterMain.getQualityMaster());
        designMasterMainModel.setDesignName(designMasterMain.getDesignName());
        designMasterMainModel.setPick(designMasterMain.getPick());
        designMasterMainModel.setWarp(designMasterMain.getWarp());
        designMasterMainModel.setWeft(designMasterMain.getWeft());
        designMasterMainModel.setWeftWestage(designMasterMain.getWeftWestage());
        designMasterMainModel.setReed(designMasterMain.getReed());
        designMasterMainModel.setWidth(designMasterMain.getWidth());
        designMasterMainModel.setReedSpace(designMasterMain.getReedSpace());
        List<DesignMasterDetails>  designMasterDetails = designMasterDetailService.getDataById(Id);
        designMasterMainModel.setDesignMasterDetailsModel(designMasterDetailService.getDataFromEntity(designMasterDetails));
        return designMasterMainModel;
    }

    @Override
    public List<DesignMasterMain> findAllByOrderByQualityMaster_QualityNameAsc() {
        return designMasterMainRepo.findAllByOrderByQualityMaster_QualityNameAsc();
    }

    @Override
    public List<DesignMasterMain> findAllSortedByQualityName() {
        return designMasterMainRepo.findAllSortedByQualityName();
    }

    @Override
    public List<DesignMasterMain> findAllByOrderByDesignNameAsc() {
        return designMasterMainRepo.findAllByOrderByDesignNameAsc();
    }
}
