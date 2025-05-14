package com.kj.textile.TextileERP.listner;

import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.events.RegistrationCompleteEvent;
import com.kj.textile.TextileERP.services.UserMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListner  implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    UserMasterService userMaserService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        UserMaser userMaser = event.getUserMaser();
        String token = UUID.randomUUID().toString();
        userMaserService.saveVerificationToken(token,userMaser);
        String url = event.getApplicationUrl()+"/verificationToken?token="+ token;
        log.info("Click link verify acc {}",url);
    }
}
