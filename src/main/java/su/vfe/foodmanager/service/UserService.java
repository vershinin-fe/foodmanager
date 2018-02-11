package su.vfe.foodmanager.service;

import su.vfe.foodmanager.model.User;
import su.vfe.foodmanager.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    void enable(int id, boolean enable);
}
