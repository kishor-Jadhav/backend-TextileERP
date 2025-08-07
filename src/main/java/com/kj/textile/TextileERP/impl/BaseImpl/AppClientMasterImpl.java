package com.kj.textile.TextileERP.impl.BaseImpl;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientMasterModel;
import com.kj.textile.TextileERP.repo.BaseRepo.AppClientMasterRepo;
import com.kj.textile.TextileERP.repo.BaseRepo.AppClientProjectMasterRepo;
import com.kj.textile.TextileERP.services.BaseService.AppClientMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
@Service
public class AppClientMasterImpl implements AppClientMasterService {
    @Autowired
    AppClientMasterRepo appClientMasterRepo;
    @Autowired
    AppClientProjectMasterRepo appClientProjectMasterRepo;
    @Override
    public List<AppClientMaster> getAllList() {
        return appClientMasterRepo.findAll();
    }

    @Override
    public AppClientMaster saveForm(AppClientMasterModel requestModelData) {
        AppClientMaster entityData = new AppClientMaster();
        int maxId = appClientMasterRepo.getMaxId();
        int year = Year.now().getValue();
        String generatedId = String.format("%d%05d",year,maxId);
        entityData.setClientName(requestModelData.getClientName());
        entityData.setClientUniqueId(generatedId);
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterRepo.findByAppClientProjectId(requestModelData.getAppClientProjectMaster().getAppClientProjectId()) ;
        entityData.setAppClientProjectMaster(appClientProjectMaster);
        appClientMasterRepo.save(entityData);
        return entityData;
    }

    @Override
    public AppClientMaster updateForm(AppClientMasterModel requestModelData) {
        AppClientMaster entityData = appClientMasterRepo.findByAppClientId(requestModelData.getAppClientId());
        entityData.setClientName(requestModelData.getClientName());
        AppClientProjectMaster appClientProjectMaster = appClientProjectMasterRepo.findByAppClientProjectId(requestModelData.getAppClientProjectMaster().getAppClientProjectId()) ;
        entityData.setAppClientProjectMaster(appClientProjectMaster);
        appClientMasterRepo.save(entityData);
        return entityData;
    }

    @Override
    public AppClientMaster getDataById(Long Id) {
        return appClientMasterRepo.findByAppClientId(Id);
    }
}
