package su.vfe.foodmanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import su.vfe.foodmanager.model.Role;
import su.vfe.foodmanager.model.User;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.util.Arrays;
import java.util.List;
import static su.vfe.foodmanager.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User newUser = new User(null, "Test", "test@mail.ru", "password", Role.ROLE_USER);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("family").isEqualTo(Arrays.asList(ADMIN, USER1, USER3, newUser, USER2));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() {
        service.create(new User(null, "Duplicate", "few@mail.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() {
        service.delete(USER1_ID);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("family").isEqualTo(Arrays.asList(ADMIN, USER3, USER2));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(WRONG_ID);
    }

    @Test
    public void get() {
        User user = service.get(USER2_ID);
        assertThat(user).isEqualToIgnoringGivenFields(USER2, "family");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(WRONG_ID);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("zoidberg@mail.ru");
        assertThat(user).isEqualToIgnoringGivenFields(USER2, "family");
    }

    @Test(expected = NotFoundException.class)
    public void getByEmailNotFound() {
        service.getByEmail("wrong@mail.ru");
    }

    @Test
    public void update() {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setRole(Role.ROLE_ADMIN);
        service.update(updated);
        assertThat(service.get(USER1_ID)).isEqualToIgnoringGivenFields(updated,"family");
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertThat(all).usingElementComparatorIgnoringFields("family").isEqualTo(Arrays.asList(ADMIN, USER1, USER3, USER2));
    }

    @Test
    public void testEnable() {
        service.enable(USER1_ID, false);
        assertThat(service.get(USER1_ID).isEnabled()).isFalse();
        service.enable(USER1_ID, true);
        assertThat(service.get(USER1_ID).isEnabled()).isTrue();
    }
}
