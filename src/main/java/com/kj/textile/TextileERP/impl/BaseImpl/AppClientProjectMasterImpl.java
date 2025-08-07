package com.kj.textile.TextileERP.impl.BaseImpl;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import com.kj.textile.TextileERP.model.BaseModel.AppClientProjectMasterModel;
import com.kj.textile.TextileERP.repo.BaseRepo.AppClientMasterRepo;
import com.kj.textile.TextileERP.repo.BaseRepo.AppClientProjectMasterRepo;
import com.kj.textile.TextileERP.services.BaseService.AppClientProjectMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
@Service
public class AppClientProjectMasterImpl implements AppClientProjectMasterService {
    @Autowired
    AppClientProjectMasterRepo appClientProjectMasterRepo;
    @Autowired
    AppClientMasterRepo appClientMasterRepo;
    @Override
    public List<AppClientProjectMaster> getAllList() {
        return appClientProjectMasterRepo.findAll();
    }

    @Override
    public AppClientProjectMasterModel saveForm(AppClientProjectMasterModel requestModelData) {
        AppClientProjectMaster entityData = new AppClientProjectMaster();
        int maxId = appClientProjectMasterRepo.getMaxId();
        int year = Year.now().getValue();
        String generatedId = String.format("%d%05d",year,maxId);
        entityData.setProjectName(requestModelData.getProjectName());
        entityData.setProjectCode(generatedId);

        appClientProjectMasterRepo.save(entityData);
        return requestModelData;
    }

    @Override
    public AppClientProjectMasterModel updateForm(AppClientProjectMasterModel requestModelData) {
        AppClientProjectMaster entityData = appClientProjectMasterRepo.findByAppClientProjectId(requestModelData.getAppClientProjectId());

        entityData.setProjectName(requestModelData.getProjectName());

        appClientProjectMasterRepo.save(entityData);
        return requestModelData;
    }

    @Override
    public AppClientProjectMaster getDataById(Long Id) {
        return appClientProjectMasterRepo.findByAppClientProjectId(Id);
    }
    @Override
    public List<AppClientProjectMaster> fillProjectListByClientId(Long Id) {
        List<AppClientProjectMaster> data = new ArrayList<>();
        return data ;
    }
}
