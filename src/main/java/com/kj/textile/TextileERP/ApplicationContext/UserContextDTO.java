package com.kj.textile.TextileERP.ApplicationContext;

public class UserContextDTO {
    private String username;
    private String ip;
    private String userType;

    public UserContextDTO() {}

    public UserContextDTO(String username, String ip, String userType, String department) {
        this.username = username;
        this.ip = ip;
        this.userType = userType;

    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
