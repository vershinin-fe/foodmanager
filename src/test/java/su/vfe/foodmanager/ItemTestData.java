package su.vfe.foodmanager;

import su.vfe.foodmanager.model.Item;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static su.vfe.foodmanager.model.Item.START_SEQ;

public class ItemTestData {
    public static final int ITEM1_ID = START_SEQ + 4;
    public static final int WRONG_ID = 10;

    public static final LocalDateTime DATE_TIME_1 = LocalDateTime.of(2018, 1, 14, 15, 0, 50);
    public static final LocalDateTime DATE_TIME_2 = LocalDateTime.of(2018, 2, 14, 15, 0, 50);
    public static final LocalDateTime DATE_TIME_3 = LocalDateTime.of(2018, 3, 14, 15, 0, 50);
    public static final LocalDateTime DATE_TIME_4 = LocalDateTime.of(2018, 4, 14, 15, 0, 50);

    public static final LocalDateTime EMPTY_DATES_UP = LocalDateTime.of(2013, 4, 14, 15, 0, 50);
    public static final LocalDateTime EMPTY_DATES_DOWN = LocalDateTime.of(2014, 4, 14, 15, 0, 50);

    public static final Item ITEM1 = new Item(ITEM1_ID, "Молоко", 1, "Чебаркуль", 84, false, DATE_TIME_1, null);
    public static final Item ITEM2 = new Item(ITEM1_ID + 1, "Хлеб", 1, "Бородинский", 30, false, DATE_TIME_1, null);
    public static final Item ITEM3 = new Item(ITEM1_ID + 2, "Кефир", 1, "Чебаркуль", 89, false, DATE_TIME_1, null);
    public static final Item ITEM4 = new Item(ITEM1_ID + 3, "Творог", 1, "Чебаркуль", 125, false, DATE_TIME_1, null);
    public static final Item ITEM5 = new Item(ITEM1_ID + 4, "Сыр", 1, "Артур", 200, true, DATE_TIME_2, null);
    public static final Item ITEM6 = new Item(ITEM1_ID + 5, "Яблоки", 5, "Сезонные", 80, true, DATE_TIME_3, null);
    public static final Item ITEM7 = new Item(ITEM1_ID + 6, "Бананы", 10, "", 60, false, DATE_TIME_4, null);

    public static final List<Item> ITEMS = Arrays.asList(ITEM1, ITEM2, ITEM3, ITEM4, ITEM5, ITEM6, ITEM7);

    public static Item getCreated() {
        return new Item(null, "Что-то забыла", 1, "", 60, false, DATE_TIME_4.plusYears(1L), null);
    }

    public static Item getUpdated() {
        return new Item(ITEM1_ID, "Молоко", 1, "Первый вкус", 89, false, DATE_TIME_1, null);
    }
}
