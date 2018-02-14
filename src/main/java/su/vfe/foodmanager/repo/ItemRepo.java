package su.vfe.foodmanager.repo;

import su.vfe.foodmanager.model.Item;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemRepo {
    // null if item do not belong to familyId
    Item get(int id, int familyId);

    // ORDERED createDate asc
    List<Item> getAll(int familyId);

    // ORDERED createDate asc
    List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed, int familyId);

    // null if updated item do not belong to familyId
    Item save(Item item, int familyId);

    // false if item do not belong to familyId
    boolean delete(int id, int familyId);
}