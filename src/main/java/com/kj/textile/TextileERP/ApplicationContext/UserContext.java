package com.kj.textile.TextileERP.ApplicationContext;


import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private static final ThreadLocal<UserContextDTO> userHolder = new ThreadLocal<>();

    public static void set(UserContextDTO user) {
        userHolder.set(user);
    }

    public static UserContextDTO get() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}
