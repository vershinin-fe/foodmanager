package su.vfe.foodmanager.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import su.vfe.foodmanager.model.User;
import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepo crudRepo;

    @Override
    public User save(User user) {
        return crudRepo.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepo.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepo.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudRepo.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudRepo.findAll(SORT_NAME_EMAIL);
    }
}
