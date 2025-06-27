package com.kj.textile.TextileERP.AppDataInitializer;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppContext {
    private final Map<String, String> globalSettings = new HashMap<>();
    private final Map<String, Map<String, String>> userSettings = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        globalSettings.put("defaultLanguage", "en");

        // Simulate loading some predefined user settings
        Map<String, String> adminSettings = new HashMap<>();
        adminSettings.put("theme", "dark");
        adminSettings.put("language", "en");

        userSettings.put("admin", adminSettings);

        Map<String, String> userSettings1 = new HashMap<>();
        userSettings1.put("theme", "light");
        userSettings1.put("language", "fr");
        userSettings1.put("isTaskStart", "false");

        userSettings.put("user123", userSettings1);

        System.out.println("User settings initialized");
        Map<String, Map<String, String>> allUsers = getAllUserSettings();
        System.out.println("allUsers: " + allUsers);
        allUsers.forEach((user, settings) -> {
            System.out.println("User: " + user);
            settings.forEach((key, value) -> System.out.println("  " + key + " = " + value));
        });

    }

    public void setUserSetting(String username, String key, String value) {
        userSettings.computeIfAbsent(username, u -> new HashMap<>()).put(key, value);
    }

    public String getUserSetting(String username, String key) {
        return userSettings.getOrDefault(username, new HashMap<>()).get(key);
    }

    public String getGlobalSetting(String key) {
        return globalSettings.get(key);
    }

    public Map<String, Map<String, String>> getAllUserSettings() {
        return userSettings;
    }
}
