package su.vfe.foodmanager.service;

import su.vfe.foodmanager.model.Item;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemService {
    Item get(int id, int familyId) throws NotFoundException;

    List<Item> getAll(int familyId);

    List<Item> getByStatus(boolean closed, int familyId);

    List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed, int familyId);

    Item create(Item item, int familyId);

    Item update(Item item, int familyId);

    void delete(int id, int familyId) throws NotFoundException;

    void close(int id, boolean close, int familyId);
}
