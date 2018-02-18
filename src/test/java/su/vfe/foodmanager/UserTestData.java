package su.vfe.foodmanager;

import su.vfe.foodmanager.model.Role;
import su.vfe.foodmanager.model.User;

import java.util.List;

import static su.vfe.foodmanager.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER1_ID = START_SEQ + 2;
    public static final int USER2_ID = START_SEQ + 3;
    public static final int USER3_ID = START_SEQ + 4;
    public static final int ADMIN_ID = 1;
    public static final int WRONG_ID = 10;

    public static final User USER1 = new User(USER1_ID, "Few", "few@mail.ru", "password", Role.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "Zoidberg", "zoidberg@mail.ru", "password", Role.ROLE_USER);
    public static final User USER3 = new User(USER3_ID, "FreeUser", "freeuser@mail.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@mail.ru", "password", Role.ROLE_ADMIN);
}