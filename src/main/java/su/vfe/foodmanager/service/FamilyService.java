package su.vfe.foodmanager.service;

import su.vfe.foodmanager.model.Family;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.util.List;

public interface FamilyService {
    Family get(int id) throws NotFoundException;

    List<Family> getAll();

    Family create(Family family);

    void update(Family family);

    void delete(int id) throws NotFoundException;

    Family getWithUsers(int id);
}
