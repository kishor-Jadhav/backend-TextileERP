package com.kj.textile.TextileERP.services.BusinessService.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.ShiftMaster;
import com.kj.textile.TextileERP.model.Master.LoomMasterModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LoomMasterService {
    List<LoomMaster> getLoomMasterList();

    Page<LoomMaster> getLoomMasterPagableList(int page, int size);

    List<ShiftMaster> getShiftMasterList();



    String deleteLoomData(Long loomId);

    String saveLoomMaster(List<LoomMasterModel> loomMasterModel);
}
