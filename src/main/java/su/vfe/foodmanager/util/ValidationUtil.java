package su.vfe.foodmanager.util;

import su.vfe.foodmanager.HasId;
import su.vfe.foodmanager.model.AbstractBaseEntity;
import su.vfe.foodmanager.model.Family;
import su.vfe.foodmanager.model.User;
import su.vfe.foodmanager.util.exception.FamilyMembershipChangeException;
import su.vfe.foodmanager.util.exception.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalArgumentException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static void checkUserAddingPossible(User user) {
        int userId = user.getId();
        if (userId == AbstractBaseEntity.ADMIN_ID || user.getFamily() != null) {
            throw new FamilyMembershipChangeException("id=" + userId);
        }
    }

    public static void checkUserRemovingPossible(User user, Family family) {
        if (!family.getUsers().contains(user)) {
            throw new FamilyMembershipChangeException("id=" + user.getId());
        }
    }
}