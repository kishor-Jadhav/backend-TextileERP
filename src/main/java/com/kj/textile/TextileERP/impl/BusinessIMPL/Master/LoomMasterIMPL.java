package com.kj.textile.TextileERP.impl.BusinessIMPL.Master;

import com.kj.textile.TextileERP.Exceptions.ResouceNotFoundException;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.ShiftMaster;
import com.kj.textile.TextileERP.model.Master.LoomMasterModel;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.LoomMasterRepo;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.ShiftMasterRepo;
import com.kj.textile.TextileERP.services.BusinessService.Master.LoomMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LoomMasterIMPL implements LoomMasterService {
    @Autowired
    LoomMasterRepo loomMasterRepo;

    @Autowired
    ShiftMasterRepo shiftMasterRepo;
    @Override
    public List<LoomMaster> getLoomMasterList() {

        return loomMasterRepo.findAll();
    }

    @Override
    public Page<LoomMaster> getLoomMasterPagableList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return loomMasterRepo.findAll(pageable);
    }

    @Override
    public List<ShiftMaster> getShiftMasterList() {
        return shiftMasterRepo.findAll();
    }

    @Override
    public String deleteLoomData(Long loomId){

        if(!loomMasterRepo.existsById(loomId)){
            throw  new ResouceNotFoundException("Record not find");
        }
        loomMasterRepo.deleteById(loomId);
        return  "success";
    }

    @Override
    public  String saveLoomMaster(List<LoomMasterModel> loomMasterModel) {
        if (loomMasterModel == null || loomMasterModel.isEmpty()) {
            return "No data to save";
        }
        loomMasterRepo.deleteAll();
        Long maxId = loomMasterRepo.findMaxLoomMasterId();
        Long newId = (maxId != null) ? maxId + 1 : 1L;
        // Step 2: Convert models to entities
        List<LoomMaster> loomMasters = new ArrayList<>();

        for (LoomMasterModel data : loomMasterModel) {
            LoomMaster loomMaster = new LoomMaster();
            loomMaster.setLoomNo(data.getLoomNo());
            loomMaster.setUnitId(data.getUnitId());
            loomMaster.setLoomMasterDetailId(newId);
            ShiftMaster shiftMaster = shiftMasterRepo.findByShiftId(data.getShiftId());
            if (shiftMaster == null) {
                throw new IllegalArgumentException("Invalid Shift ID: " + data.getShiftId());
            }

            loomMaster.setShiftMaster(shiftMaster);
            loomMasters.add(loomMaster);
        }

        // Step 3: Bulk insert
        loomMasterRepo.saveAll(loomMasters);

        return "Success";

    }
}
