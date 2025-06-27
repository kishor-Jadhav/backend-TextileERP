package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterDetails;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.YarnMaster;
import com.kj.textile.TextileERP.model.Master.DesignMasterDetailsModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.DesignMasterDetailsRepo;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.DesignMasterMainRepo;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.YarnMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.DesignMasterDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DesignMasterDetailIMPL implements DesignMasterDetailService {
   @Autowired
    DesignMasterDetailsRepo designMasterDetailsRepo;

   @Autowired
    DesignMasterMainRepo designMasterMainRepo;

   @Autowired
   YarnMasterRepo yarnMasterRepo;
   public DesignMasterDetailIMPL(DesignMasterDetailsRepo designMasterDetailsRepo,DesignMasterMainRepo designMasterMainRepo){
       this.designMasterDetailsRepo =designMasterDetailsRepo;
       this.designMasterMainRepo =designMasterMainRepo;
   }
    @Override
    public List<DesignMasterDetails> getAllRecord() {
        return List.of();
    }
    @Transactional
    @Override
    public String saveForm(List<DesignMasterDetailsModel> designMasterDetailsModel, Long Id) {
        List<DesignMasterDetails> list = new ArrayList<>();
        designMasterDetailsRepo.deleteAllByDesignMasterMainId(Id);
        for(DesignMasterDetailsModel modelData : designMasterDetailsModel){
            YarnMaster count = yarnMasterRepo.findById(modelData.getCountId())
                    .orElseThrow(() -> new RuntimeException("Count not found"));
            DesignMasterDetails designMasterDetails = new DesignMasterDetails();
            designMasterDetails.setDesignMasterMainId(Id);
            designMasterDetails.setYarnMaster(count);
            designMasterDetails.setPick(modelData.getPick());
            designMasterDetails.setRepeat(modelData.getRepeat());
            designMasterDetails.setActualWt(modelData.getActualWt());
            list.add(designMasterDetails);

        }
        designMasterDetailsRepo.saveAll(list);

        return "OK";
    }

    @Override
    public DesignMasterDetails updateForm(DesignMasterDetailsModel designMasterDetailsModel) {
        return null;
    }

    @Override
    public List<DesignMasterDetails> getDataById(Long Id) {
        return designMasterDetailsRepo.findByDesignMasterMainId(Id);
    }
    @Override
    public List<DesignMasterDetailsModel> getDataFromEntity(List<DesignMasterDetails> listDesignMasterDetails) {
        List<DesignMasterDetailsModel> list = new ArrayList<>();
        for(DesignMasterDetails designMasterDetails : listDesignMasterDetails){
            DesignMasterDetailsModel designMasterDetailsModel = new DesignMasterDetailsModel();

            designMasterDetailsModel.setCountDetail(designMasterDetails.getYarnMaster());
            designMasterDetailsModel.setDesignMasterMainId(designMasterDetails.getDesignMasterMainId());

            designMasterDetailsModel.setPick(designMasterDetails.getPick());
            designMasterDetailsModel.setRepeat(designMasterDetails.getRepeat());
            designMasterDetailsModel.setActualWt(designMasterDetails.getActualWt());
            list.add(designMasterDetailsModel);
        }
       return list;
    }
}
