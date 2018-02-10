package su.vfe.foodmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import su.vfe.foodmanager.model.Family;
import su.vfe.foodmanager.repo.FamilyRepo;
import su.vfe.foodmanager.util.exception.NotFoundException;
import static su.vfe.foodmanager.util.ValidationUtil.*;
import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepo repo;

    @Autowired
    public FamilyServiceImpl(FamilyRepo repo) {
        this.repo = repo;
    }

    @Override
    public Family get(int id) throws NotFoundException {
        return checkNotFoundWithId(repo.get(id), id);
    }

    @Override
    public List<Family> getAll() {
        return repo.getAll();
    }

    @Override
    public Family create(Family family) {
        Assert.notNull(family, "family must not be null");
        return repo.save(family);
    }

    @Override
    public void update(Family family) {
        Assert.notNull(family, "family must not be null");
        checkNotFoundWithId(repo.save(family), family.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repo.delete(id), id);
    }

    @Override
    public Family getWithUsers(int id) {
        return checkNotFoundWithId(repo.getWithUsers(id), id);
    }
}
