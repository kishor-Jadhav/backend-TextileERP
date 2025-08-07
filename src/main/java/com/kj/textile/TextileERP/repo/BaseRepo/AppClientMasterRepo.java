package com.kj.textile.TextileERP.repo.BaseRepo;

import com.kj.textile.TextileERP.entity.AppClientMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppClientMasterRepo extends JpaRepository<AppClientMaster,Long> {
    @Query("Select COALESCE(max(S.appClientId),0) + 1 as next_Id from AppClientMaster as  S ")
    int getMaxId();

    AppClientMaster findByAppClientId(Long Id);
}
