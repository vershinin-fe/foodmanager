package su.vfe.foodmanager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import su.vfe.foodmanager.model.User;
import java.util.Collections;
import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, Collections.singletonList(user.getRole()));
        this.user = user;
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public int getId() {
        return user.getId();
    }

    public static int id() {
        return get().user.getId();
    }

    public static int family_id() {
        return get().user.getFamily().getId();
    }

    public void update(User newUser) {
        user = newUser;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}