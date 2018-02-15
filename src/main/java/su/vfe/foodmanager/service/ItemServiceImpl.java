package su.vfe.foodmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import su.vfe.foodmanager.model.Item;
import su.vfe.foodmanager.repo.ItemRepo;
import su.vfe.foodmanager.util.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import static su.vfe.foodmanager.util.ValidationUtil.*;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo repo;

    @Autowired
    public ItemServiceImpl(ItemRepo repo) {
        this.repo = repo;
    }

    @Override
    public Item get(int id, int familyId) throws NotFoundException {
        return checkNotFoundWithId(repo.get(id, familyId), id);
    }

    @Override
    public List<Item> getAll(int familyId) {
        return repo.getAll(familyId);
    }

    @Override
    public List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed, int familyId) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");
        return repo.getBetweenByStatus(startDate, endDate, closed, familyId);
    }

    @Override
    public Item create(Item item, int familyId) {
        Assert.notNull(item, "item must not be null");
        return repo.save(item, familyId);
    }

    @Override
    public Item update(Item item, int familyId) {
        return checkNotFoundWithId(repo.save(item, familyId), item.getId());
    }

    @Override
    public void delete(int id, int familyId) throws NotFoundException {
        checkNotFoundWithId(repo.delete(id, familyId), id);
    }

    @Override
    public void close(int id, boolean close, int familyId) {
        Item item = get(id, familyId);
        item.setClosed(close);
        item.setCloseDate(close ? LocalDateTime.now() : null);
        repo.save(item, familyId);
    }
}
