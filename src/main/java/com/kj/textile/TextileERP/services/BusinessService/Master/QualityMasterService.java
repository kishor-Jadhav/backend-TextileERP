package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.QualityMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.YarnMaster;
import com.kj.textile.TextileERP.model.Master.QualityMasterModel;
import com.kj.textile.TextileERP.model.Master.YarnMasterModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface QualityMasterService {
    List<QualityMaster> getAllQuality();



    QualityMaster getQualityData(Long CountId);

    QualityMaster saveQualityMaster(QualityMasterModel qualityMasterModel);

    QualityMaster updateQualityMaster(QualityMasterModel qualityMasterModel);
}
