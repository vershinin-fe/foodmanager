package su.vfe.foodmanager.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import su.vfe.foodmanager.model.Family;

import java.util.List;

@Repository
public class FamilyRepoImpl implements FamilyRepo {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudFamilyRepo repo;

    @Override
    public Family get(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Family> getAll() {
        return repo.findAll(SORT_NAME);
    }

    @Override
    public Family save(Family family) {
        return repo.save(family);
    }

    @Override
    public boolean delete(int id) {
        return repo.delete(id) != 0;
    }

    @Override
    public Family getWithUsers(int id) {
        return repo.getWithUsers(id);
    }
}
