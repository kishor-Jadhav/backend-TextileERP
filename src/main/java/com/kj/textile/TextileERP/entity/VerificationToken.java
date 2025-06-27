package com.kj.textile.TextileERP.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {
    public  static  final  int EXPTIME =10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    Date expTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    public UserMaser getUserMaser() {
        return userMaser;
    }

    public void setUserMaser(UserMaser userMaser) {
        this.userMaser = userMaser;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id", nullable = false,
            referencedColumnName = "userId"
    )
    UserMaser userMaser;

   public VerificationToken(UserMaser userMaser, String token){
    super();
    this.token = token;
    this.userMaser = userMaser;
    this.expTime = calculateExpTime(EXPTIME);
   }
    public VerificationToken(String token){
       super();
        this.token = token;
        this.expTime = calculateExpTime(EXPTIME);
    }
    private Date calculateExpTime(int exptime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,exptime);
        return  new Date(calendar.getTime().getTime());
    }
}
