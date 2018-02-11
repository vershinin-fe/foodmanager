package su.vfe.foodmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import su.vfe.foodmanager.model.User;
import su.vfe.foodmanager.repo.UserRepo;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.util.List;
import static su.vfe.foodmanager.util.ValidationUtil.checkNotFound;
import static su.vfe.foodmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Autowired
    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repo.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repo.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repo.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repo.getByEmail(email), "email=" + email);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repo.save(user), user.getId());
    }

    @Override
    public List<User> getAll() {
        return repo.getAll();
    }

    @Override
    @Transactional
    public void enable(int id, boolean enable) {
        User user = get(id);
        user.setEnabled(enable);
        repo.save(user);
    }
}