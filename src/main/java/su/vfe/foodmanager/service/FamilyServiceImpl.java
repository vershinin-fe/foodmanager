package su.vfe.foodmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import su.vfe.foodmanager.model.Family;
import su.vfe.foodmanager.model.User;
import su.vfe.foodmanager.repo.FamilyRepo;
import su.vfe.foodmanager.util.exception.FamilyMembershipChangeException;
import su.vfe.foodmanager.util.exception.NotFoundException;
import static su.vfe.foodmanager.util.ValidationUtil.*;
import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepo familyRepo;

    private final UserService userService;

    @Autowired
    public FamilyServiceImpl(FamilyRepo familyRepo, UserService userService) {
        this.familyRepo = familyRepo;
        this.userService = userService;
    }

    @Override
    public Family get(int id) throws NotFoundException {
        return checkNotFoundWithId(familyRepo.get(id), id);
    }

    @Override
    public List<Family> getAll() {
        return familyRepo.getAll();
    }

    @Override
    public Family create(Family family) {
        Assert.notNull(family, "family must not be null");
        return familyRepo.save(family);
    }

    @Override
    public void update(Family family) {
        Assert.notNull(family, "family must not be null");
        checkNotFoundWithId(familyRepo.save(family), family.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(familyRepo.delete(id), id);
    }

    @Override
    public Family getWithUsers(int id) {
        return checkNotFoundWithId(familyRepo.getWithUsers(id), id);
    }

    @Transactional
    @Override
    public void addUser(int id, int userId) throws FamilyMembershipChangeException {
        Family family = get(id);
        User user = userService.get(userId);

        checkUserAddingPossible(user);

        user.setFamily(family);
        family.getUsers().add(user);
    }

    @Transactional
    @Override
    public void removeUser(int id, int userId) throws FamilyMembershipChangeException{
        Family family = get(id);
        User user = userService.get(userId);

        checkUserRemovingPossible(user, family);

        user.setFamily(null);
        family.getUsers().remove(user);
    }
}
