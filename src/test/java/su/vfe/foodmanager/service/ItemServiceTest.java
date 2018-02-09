package su.vfe.foodmanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import su.vfe.foodmanager.model.Item;
import static su.vfe.foodmanager.ItemTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ItemServiceTest {

    @Autowired
    protected ItemService service;

    @Test
    public void get() {
        Item actual = service.get(ITEM1_ID);
        assertThat(actual).isEqualToComparingFieldByField(ITEM1);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll()).usingFieldByFieldElementComparator().isEqualTo(ITEMS);
    }

    @Test
    public void getByStatus() {
    }

    @Test
    public void getBetweenByStatus() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void close() {
    }
}
