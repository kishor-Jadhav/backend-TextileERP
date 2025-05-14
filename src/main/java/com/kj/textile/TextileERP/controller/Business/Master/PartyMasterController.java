package com.kj.textile.TextileERP.controller.Business.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.FirmMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyCategoryMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyMaster;
import com.kj.textile.TextileERP.model.Master.FirmMasterModel;
import com.kj.textile.TextileERP.model.Master.PartyMasterModel;
import com.kj.textile.TextileERP.services.BusinessService.Master.PartyMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/")
public class PartyMasterController {
    @Autowired
    PartyMasterService partyMasterService;

    @GetMapping("getpartycategory")
    public List<PartyCategoryMaster> getPartyCategory(){
        return partyMasterService.getPartyCategory();
    }

    @PostMapping("savepartymaster")
    public PartyMaster savePartyMaster(@RequestBody PartyMasterModel partyMasterModel){
        return  partyMasterService.savePartyMaster(partyMasterModel);
    }

    @PostMapping("updatepartymaster")
    public PartyMaster updatePartyMaster(@RequestBody PartyMasterModel partyMasterModel){
        return partyMasterService.updatePartyMaster(partyMasterModel);
    }

    @GetMapping("getparty")
    public List<PartyMaster> getAllPartyData() {
        List<PartyMaster> partyData = partyMasterService.getAllPartyMasterList();
        return partyData;
    }
    @GetMapping("getpartyData/{partyId}")
    public PartyMaster getPartyData(@PathVariable String partyId) {
        PartyMaster partyData = partyMasterService.getPartyData(Long.parseLong(partyId));
        return partyData;
    }
}
