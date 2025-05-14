package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.LoomMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoomMasterRepo extends JpaRepository<LoomMaster,Long> {
    @Query("SELECT MAX(l.loomMasterId) FROM LoomMaster l")
    Long findMaxLoomMasterId();

    LoomMaster findByLoomMasterId(Long loomMasterId);


}
