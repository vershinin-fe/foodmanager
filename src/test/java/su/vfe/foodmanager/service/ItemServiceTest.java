package su.vfe.foodmanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import su.vfe.foodmanager.model.Item;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.util.Arrays;
import java.util.Collections;
import static su.vfe.foodmanager.ItemTestData.*;
import static su.vfe.foodmanager.FamilyTestData.FAMILY1_ID;
import static su.vfe.foodmanager.FamilyTestData.FAMILY2_ID;
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
        Item actual = service.get(ITEM1_ID, FAMILY1_ID);
        assertThat(actual).isEqualToIgnoringGivenFields(ITEM1, "family");
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(WRONG_ID, FAMILY1_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotBelongs() {
        service.get(ITEM1_ID, FAMILY2_ID);
    }

    @Test
    public void getAll() {
        assertThat(service.getAll(FAMILY1_ID)).usingElementComparatorIgnoringFields("family").isEqualTo(ITEMS);
    }

    @Test
    public void getBetweenByStatus() {
        assertThat(service.getBetweenByStatus(DATE_TIME_2, DATE_TIME_4, false, FAMILY1_ID)).usingElementComparatorIgnoringFields("family").isEqualTo(Collections.singletonList(ITEM7));
    }

    @Test
    public void getBetweenByStatusEmpty() {
        assertThat(service.getBetweenByStatus(EMPTY_DATES_UP, EMPTY_DATES_DOWN, false, FAMILY1_ID)).usingElementComparatorIgnoringFields("family").isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    public void create() {
        Item created = getCreated();
        service.create(created, FAMILY1_ID);
        assertThat(service.getAll(FAMILY1_ID)).usingElementComparatorIgnoringFields("family").isEqualTo(Arrays.asList(ITEM1, ITEM2, ITEM3, ITEM4, ITEM5, ITEM6, ITEM7, created));
    }

    @Test
    public void update() {
        Item updated = getUpdated();
        service.update(updated, FAMILY1_ID);
        assertThat(service.get(ITEM1_ID, FAMILY1_ID)).isEqualToIgnoringGivenFields(updated, "family");
    }

    @Test
    public void delete() {
        service.delete(ITEM1_ID, FAMILY1_ID);
        assertThat(service.getAll(FAMILY1_ID)).usingElementComparatorIgnoringFields("family").isEqualTo(Arrays.asList(ITEM2, ITEM3, ITEM4, ITEM5, ITEM6, ITEM7));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(WRONG_ID, FAMILY1_ID);
    }

    @Test
    public void close() {
        assertThat(service.get(ITEM1_ID, FAMILY1_ID).isClosed()).isFalse();
        service.close(ITEM1_ID, true, FAMILY1_ID);
        assertThat(service.get(ITEM1_ID, FAMILY1_ID).isClosed()).isTrue();
    }
}
