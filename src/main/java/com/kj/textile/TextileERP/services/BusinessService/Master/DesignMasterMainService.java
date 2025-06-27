package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterMain;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.FirmMaster;
import com.kj.textile.TextileERP.model.Master.DesignMasterMainModel;
import com.kj.textile.TextileERP.model.Master.FirmMasterModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DesignMasterMainService {
    List<DesignMasterMain> getAllRecord();

    DesignMasterMain saveForm(DesignMasterMainModel designMasterMainModel);
    DesignMasterMain updateForm(DesignMasterMainModel designMasterMainModel);
    DesignMasterMainModel getDataById(Long Id);
    List<DesignMasterMain> findAllByOrderByQualityMaster_QualityNameAsc();
    List<DesignMasterMain> findAllSortedByQualityName();
    List<DesignMasterMain> findAllByOrderByDesignNameAsc();
}
