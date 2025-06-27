package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DesignMasterDetailsRepo extends JpaRepository<DesignMasterDetails, Long> {
    @Query("Select t.pick from DesignMasterDetails as t where t.designMasterMainId = :designMasterMainId")
    List<DesignMasterDetails> findByDesignMasterMainId1(@Param("designMasterMainId") Long designMasterMainId);
    void  deleteAllByDesignMasterMainId(Long designMasterMainId);
    List<DesignMasterDetails>  findByDesignMasterMainId(Long designMasterMainId);
}
