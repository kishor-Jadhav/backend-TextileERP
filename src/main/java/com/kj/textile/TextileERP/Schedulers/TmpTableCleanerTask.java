package com.kj.textile.TextileERP.Schedulers;

import com.kj.textile.TextileERP.AppDataInitializer.AppContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.entity.BusinessEntity.Master.TMPMaster;
import com.kj.textile.TextileERP.repo.BusinessRepo.Master.TmpMasterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class TmpTableCleanerTask {

    @Autowired
    private TmpMasterRepo tmpMasterRepo;

    @Autowired
    AppContext appContext;



    @Autowired
    private com.kj.textile.TextileERP.service.TranslationService translationService;

    // Runs every 3 minutes from the last start time
    @Scheduled(fixedRate = 10000 )
    public void cleanTMPTable() {
      //  log.info("Scheduled TMP table cleanup started at: {}", LocalDateTime.now());
        try {
            String userflag = appContext.getUserSetting("userSettingConfig","isTaskStart");
            if(Objects.equals(userflag, "true")){
              //  tmpMasterRepo.deleteAll();
              //  log.info("TMP table cleanup completed successfully.");
            }

        } catch (Exception e) {
            log.error("Error during TMP table cleanup", e);
        }
    }

    @Scheduled(fixedRate = 30000, initialDelay = 30000)
    public void setuserSettingVal() {
        //appContext.setUserSetting("user123","isTaskStart","true");


    }
    // Runs every 1 minute from the last start time
    @Scheduled(fixedRate = 60000, initialDelay = 660000)
    public void insertTMPTable() {
    //    log.info("Scheduled TMP table data insert started at: {}", LocalDateTime.now());
        try {
            TMPMaster tmp = new TMPMaster();

           // String username = SecurityContextHolder.getContext().getAuthentication().getName();
            tmp.setCreatedName("Sys User");
            tmp.setCreatedTime(new Date());  // stores full timestamp (no formatting needed)

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String displayTime = formatter.format(tmp.getCreatedTime());


            tmp.setData1(String.valueOf(new Date(System.currentTimeMillis())));

           // tmpMasterRepo.save(tmp);
          //  System.out.println("Created Time: " + displayTime);
           // log.info("TMP table insert completed successfully.");
        } catch (Exception e) {
            log.error("Error during TMP table insert", e);
        }
    }

    @Scheduled(fixedRate = 10000, initialDelay = 10000)
    public void gettranslationVal() {
        String labelEn = translationService.getTranslation("master.partyMaster.partyName", "en");
      //  System.out.println("EN: " + labelEn);

        String labelMr = translationService.getTranslation("master.partyMaster.partyCategories.categoryName1", "mr");
       // System.out.println("EN: " + labelMr);
    }

    @Scheduled(fixedRate = 10000, initialDelay = 10000)
    public void scheduleAfterLogin() {
       String strval= appContext.getUserSetting("userSettingConfig","loginUser");
      // log.info("After Login User - " + strval);
    }
}
