package com.kj.textile.TextileERP.repo.BusinessRepo.Master;

import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterDetails;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.DesignMasterMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignMasterMainRepo extends JpaRepository<DesignMasterMain, Long> {
DesignMasterMain findByDesignMasterMainId(Long Id);

@Query("Select Max(t.designMasterMainId) from DesignMasterMain as t")
    Long findMaxId();

    // Fetch all sorted by quality name
    List<DesignMasterMain> findAllByOrderByQualityMaster_QualityNameAsc();

    // Fetch all sorted by quality name - Query
    @Query("SELECT d FROM DesignMasterMain d ORDER BY d.qualityMaster.qualityName ASC")
    List<DesignMasterMain> findAllSortedByQualityName();

    // Fetch all sorted by design name
    List<DesignMasterMain> findAllByOrderByDesignNameAsc();

}
