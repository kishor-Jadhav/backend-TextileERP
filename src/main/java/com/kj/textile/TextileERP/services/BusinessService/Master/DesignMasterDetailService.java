package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterDetails;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterMain;
import com.kj.textile.TextileERP.model.Master.DesignMasterDetailsModel;
import com.kj.textile.TextileERP.model.Master.DesignMasterMainModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DesignMasterDetailService {
    List<DesignMasterDetails> getAllRecord();



    String saveForm(List<DesignMasterDetailsModel> designMasterDetailsModel, Long Id);

    DesignMasterDetails updateForm(DesignMasterDetailsModel designMasterDetailsModel);
    List<DesignMasterDetails> getDataById(Long Id);

    List<DesignMasterDetailsModel> getDataFromEntity(List<DesignMasterDetails> listDesignMasterDetails);
}
