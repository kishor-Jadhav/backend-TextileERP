package com.kj.textile.TextileERP.repo.BaseRepo;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import com.kj.textile.TextileERP.entity.AppClientProjectMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppClientProjectMasterRepo extends JpaRepository<AppClientProjectMaster, Long> {
    @Query("Select COALESCE(max(S.appClientProjectId),0) + 1 as next_Id from AppClientProjectMaster as  S ")
    int getMaxId();

    AppClientProjectMaster findByAppClientProjectId(Long Id);
    // Find by AppClientMaster's appClientId
   // List<AppClientProjectMaster> findByAppClientMaster_AppClientId(Long appClientId);
}
