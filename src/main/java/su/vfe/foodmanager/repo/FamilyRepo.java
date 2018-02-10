package su.vfe.foodmanager.repo;

import su.vfe.foodmanager.model.Family;

import java.util.List;

public interface FamilyRepo {
    // null if not found
    Family get(int id);

    List<Family> getAll();

    Family save(Family family);

    // false if not found
    boolean delete(int id);

    // null if not found
    Family getWithUsers(int id);
}
