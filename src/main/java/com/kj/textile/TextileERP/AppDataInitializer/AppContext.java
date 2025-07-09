package com.kj.textile.TextileERP.AppDataInitializer;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppContext {
    private final Map<String, String> globalSettings = new HashMap<>();
    private final Map<String, Map<String, String>> loginUserSettings = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {


        this.initializeAdminConfigData();
        this.initializeLoginUserConfigData();
        this.displayConfigData();

    }
    void initializeLoginUserConfigData() {
        Map<String, String> userSettings = new HashMap<>();
        userSettings.put("loginUser", "");
        userSettings.put("language", "");
        userSettings.put("isTaskStart", "false");

        loginUserSettings.put("userSettingConfig", userSettings);
    }
    void initializeAdminConfigData() {
        globalSettings.put("defaultLanguage", "en");

        // Simulate loading some predefined user settings
        Map<String, String> adminSettings = new HashMap<>();
        adminSettings.put("theme", "dark");
        adminSettings.put("language", "en");

        loginUserSettings.put("admin", adminSettings);
    }

    void displayConfigData(){
        System.out.println("User settings initialized");
        Map<String, Map<String, String>> allUsers = getAllUserSettings();
        System.out.println("allUsers: " + allUsers);
        allUsers.forEach((user, settings) -> {
            System.out.println("User: " + user);
            settings.forEach((key, value) -> System.out.println("  " + key + " = " + value));
        });
    }

    public void setUserSetting(String username, String key, String value) {
        loginUserSettings.computeIfAbsent(username, u -> new HashMap<>()).put(key, value);
    }

    public String getUserSetting(String username, String key) {
        return loginUserSettings.getOrDefault(username, new HashMap<>()).get(key);
    }

    public String getGlobalSetting(String key) {
        return globalSettings.get(key);
    }

    public Map<String, Map<String, String>> getAllUserSettings() {
        return loginUserSettings;
    }



}
