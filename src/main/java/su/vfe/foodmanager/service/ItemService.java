package su.vfe.foodmanager.service;

import su.vfe.foodmanager.model.Item;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemService {
    Item get(int id) throws NotFoundException;

    List<Item> getAll();

    List<Item> getByStatus(boolean closed);

    List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed);

    Item create(Item item);

    Item update(Item item);

    void delete(int id) throws NotFoundException;

    void close(int id, boolean close);
}
