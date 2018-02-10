package su.vfe.foodmanager;

import su.vfe.foodmanager.model.AbstractBaseEntity;

public class AuthorizedUser {
    private static int id = AbstractBaseEntity.START_SEQ + 2;

    private static int family_id = AbstractBaseEntity.START_SEQ;

    public static int id() {
        return id;
    }

    public static int family_id() {
        return family_id;
    }
}