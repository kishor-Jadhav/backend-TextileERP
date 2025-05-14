package com.kj.textile.TextileERP.events;


import com.kj.textile.TextileERP.entity.UserMaser;
import com.kj.textile.TextileERP.repo.UserMaserRepo;
import com.kj.textile.TextileERP.services.UserMasterService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    UserMaser userMaser;
    String applicationUrl;

   public RegistrationCompleteEvent(UserMaser userMaser, String applicationUrl){
       super(userMaser);
       this.userMaser = userMaser;
       this.applicationUrl = applicationUrl;
   }

}
