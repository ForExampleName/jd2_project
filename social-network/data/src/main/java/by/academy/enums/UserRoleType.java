package by.academy.enums;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public enum UserRoleType {
    ADMIN(2), USER(1);

    private final int level;

    UserRoleType(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static int findMaxLevelInArray(UserRoleType[] roles) {
        if (roles.length == 0)
            return -1;
        return Arrays.stream(roles)
                .max(Comparator.comparingInt(UserRoleType::getLevel))
                .get()
                .getLevel();
    }

    public static int findMaxLevelInCollection(Collection<UserRoleType> roles) {
        if (roles.size() == 0)
            return -1;
        return roles.stream()
                .max(Comparator.comparingInt(UserRoleType::getLevel))
                .get()
                .getLevel();
    }
}
