package su.vfe.foodmanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import su.vfe.foodmanager.model.Family;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.util.Arrays;
import java.util.Collections;

import static su.vfe.foodmanager.FamilyTestData.*;
import static su.vfe.foodmanager.UserTestData.USER1;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class FamilyServiceTest {

    @Autowired
    private FamilyService service;

    @Test
    public void get() {
        Family actual = service.get(FAMILY1_ID);
        assertThat(actual).isEqualToIgnoringGivenFields(FAMILY1, "users", "items");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(WRONG_ID);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("users", "items").isEqualTo(FAMILIES);
    }

    @Test
    public void create() {
        Family created = getCreated();
        service.create(created);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("users", "items").isEqualTo(Arrays.asList(FAMILY1, FAMILY2, created));
    }

    @Test
    public void update() {
        Family updated = getUpdated();
        service.update(updated);
        assertThat(service.get(FAMILY1_ID)).isEqualToIgnoringGivenFields(updated, "users", "items");
    }

    @Test
    public void delete() {
        service.delete(FAMILY1_ID);
        assertThat(service.getAll()).usingElementComparatorIgnoringFields("users", "items").isEqualTo(Collections.singletonList(FAMILY2));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(WRONG_ID);
    }

    @Test
    public void getWithUsers() {
        Family family = service.getWithUsers(FAMILY1_ID);
        assertThat(family).isEqualToIgnoringGivenFields(FAMILY1,"users", "items");
        assertThat(family.getUsers()).usingElementComparatorIgnoringFields("family").isEqualTo(Collections.singletonList(USER1));
    }

    @Test(expected = NotFoundException.class)
    public void getWithUsersNotFound() {
        service.getWithUsers(WRONG_ID);
    }
}
