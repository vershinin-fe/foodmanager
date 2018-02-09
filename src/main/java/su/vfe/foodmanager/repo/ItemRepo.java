package su.vfe.foodmanager.repo;

import su.vfe.foodmanager.model.Item;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemRepo {
    // null if not found
    Item get(int id);

    List<Item> getAll();

    List<Item> getByStatus(boolean closed);

    List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed);

    Item save(Item item);

    // false if not found
    boolean delete(int id);
}
