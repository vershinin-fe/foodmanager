package su.vfe.foodmanager;

import su.vfe.foodmanager.model.Item;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static su.vfe.foodmanager.model.Item.START_SEQ;

public class ItemTestData {
    public static final int ITEM1_ID = START_SEQ;

    public static final Item ITEM1 = new Item(ITEM1_ID, "Молоко", 1.5, "Чебаркуль", 84, LocalDateTime.of(2018, 1, 14, 15, 0, 50), null);
    public static final Item ITEM2 = new Item(ITEM1_ID + 1, "Хлеб", 1.0, "Бородинский", 30, LocalDateTime.of(2018, 1, 14, 15, 0, 50), null);
    public static final Item ITEM3 = new Item(ITEM1_ID + 2, "Кефир", 1.0, "Чебаркуль", 89, LocalDateTime.of(2018, 1, 14, 15, 0, 50), null);
    public static final Item ITEM4 = new Item(ITEM1_ID + 3, "Творог", 0.5, "Чебаркуль", 125, LocalDateTime.of(2018, 1, 14, 15, 0, 50), null);
    public static final Item ITEM5 = new Item(ITEM1_ID + 4, "Сыр", 0.3, "Артур", 200, LocalDateTime.of(2018, 1, 14, 15, 0, 50), null);
    public static final Item ITEM6 = new Item(ITEM1_ID + 5, "Яблоки", 1.5, "Сезонные", 80, LocalDateTime.of(2018, 1, 14, 15, 0, 50), null);
    public static final Item ITEM7 = new Item(ITEM1_ID + 6, "Бананы", 1.5, "", 60, LocalDateTime.of(2018, 1, 14, 15, 0, 50), null);

    public static final List<Item> ITEMS = Arrays.asList(ITEM1, ITEM2, ITEM3, ITEM4, ITEM5, ITEM6, ITEM7);

    /*public static Meal getCreated() {
        return new Meal(null, of(2015, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }*/
}
