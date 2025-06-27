package com.kj.textile.TextileERP.controller.Business.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.PartyCategoryMaster;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.ShiftMaster;
import com.kj.textile.TextileERP.model.Master.LoomMasterModel;
import com.kj.textile.TextileERP.services.BusinessService.Master.LoomMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@ResponseBody
@RestController
@RequestMapping("/api/v1/")
public class LoomMasterController {
    @Autowired
    LoomMasterService loomMasterService;


    @GetMapping("getshiftmaster")
    public List<ShiftMaster> getShiftMaster(){
        return loomMasterService.getShiftMasterList();
    }

    @GetMapping("getloommaster")
    public List<LoomMaster> getLoomMaster(){
        return loomMasterService.getLoomMasterList();
    }

    @GetMapping("/getloommasterpageble")
    public Page<LoomMaster> getLoomMasterList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return loomMasterService.getLoomMasterPagableList(page, size);
    }

    @GetMapping("/getloommasteronscroll")
    public Page<LoomMaster> getLoomMasterListOnScroll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return loomMasterService.getLoomMasterPagableList(page, size);
    }

    @PostMapping("saveloomaster")
    public String saveLoomMaster(@RequestBody List<LoomMasterModel> loomMasterModel){
        return loomMasterService.saveLoomMaster(loomMasterModel);
    }

    @GetMapping("deleteloomaster/{loomId}")
    public ResponseEntity<String> deleteLoomMaster(@PathVariable String loomId){
        String relVal = loomMasterService.deleteLoomData(Long.parseLong(loomId));
        if(Objects.equals(relVal, "success")){
            return ResponseEntity.ok("Record deleted successfully");
        } else {
            return ResponseEntity.ok("Record not found");
        }
    }
}
