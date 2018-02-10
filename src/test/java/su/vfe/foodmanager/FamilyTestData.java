package su.vfe.foodmanager;

import su.vfe.foodmanager.model.Family;
import java.util.Arrays;
import java.util.List;
import static su.vfe.foodmanager.model.Item.START_SEQ;

public class FamilyTestData {
    public static final int FAMILY1_ID = START_SEQ;
    public static final int FAMILY2_ID = START_SEQ + 1;
    public static final int WRONG_ID = 10;

    public static final Family FAMILY1 = new Family(FAMILY1_ID, "Вершинины");
    public static final Family FAMILY2 = new Family(FAMILY2_ID, "Дерябины");

    public static final List<Family> FAMILIES = Arrays.asList(FAMILY1, FAMILY2);

    public static Family getCreated() {
        return new Family(null, "Крысановы");
    }

    public static Family getUpdated() {
        return new Family(FAMILY1_ID, "Вершинины-Власовы");
    }
}
