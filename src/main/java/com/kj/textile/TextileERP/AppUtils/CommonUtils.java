package com.kj.textile.TextileERP.AppUtils;

import com.kj.textile.TextileERP.ApplicationContext.UserContext;
import com.kj.textile.TextileERP.ApplicationContext.UserContextDTO;
import com.kj.textile.TextileERP.entity.UserRoles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CommonUtils {
    // Prevent instantiation of the utility class
    private CommonUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // --- Example 1: String Utility ---
    /**
     * Checks if a string is null, empty, or contains only whitespace.
     * @param str The string to check.
     * @return true if null, empty, or blank; false otherwise.
     */
    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public int getLoginUserPriority(){
        int loginUserPriority =0;
        UserContextDTO userContext = UserContext.get();
        Optional<Integer> userRolePriorityOption = userContext.getRoles().stream().map(item->item.getRolePriority())
                .findFirst();

        if (userRolePriorityOption.isPresent()) {
            loginUserPriority = userRolePriorityOption.get();

        }
        return loginUserPriority;
    }

    public boolean isUserSuperAdmin(){
        UserContextDTO userContext = UserContext.get();
        Optional<Integer> userRolePriorityOption = userContext.getRoles().stream().map(item->item.getRolePriority())
                .findFirst();
        // Check User Has SuperId role
        List<String> rolesToContains = List.of("ROLE_SUPERADMIN");
        boolean has_ROLE_SUPERADMIN =  userContext.getRoles().stream()
                .map(UserRoles::getRoleName)
                .anyMatch(rolesToContains::contains);
        return has_ROLE_SUPERADMIN;
    }

    // --- Example 2: Collection Utility ---
    /**
     * Checks if a collection is null or empty.
     * @param collection The collection to check.
     * @return true if null or empty; false otherwise.
     */
    public static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    // --- Example 3: Date/Time Utility ---
    /**
     * Formats a LocalDateTime object into a common string format (e.g., "yyyy-MM-dd HH:mm:ss").
     * @param dateTime The LocalDateTime object.
     * @return A formatted string representation.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

}
