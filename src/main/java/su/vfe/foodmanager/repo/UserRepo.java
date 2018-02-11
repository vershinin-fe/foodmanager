package su.vfe.foodmanager.repo;

import su.vfe.foodmanager.model.User;
import java.util.List;

public interface UserRepo {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
