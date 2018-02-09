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
    public Item get(int id) throws NotFoundException {
        return checkNotFoundWithId(repo.get(id), id);
    }

    @Override
    public List<Item> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Item> getByStatus(boolean closed) {
        return repo.getByStatus(closed);
    }

    @Override
    public List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");
        return repo.getBetweenByStatus(startDate, endDate, closed);
    }

    @Override
    public Item create(Item item) {
        Assert.notNull(item, "item must not be null");
        return repo.save(item);
    }

    @Override
    public Item update(Item item) {
        return checkNotFoundWithId(repo.save(item), item.getId());
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repo.delete(id), id);
    }

    @Override
    public void close(int id, boolean close) {
        Item item = get(id);
        item.setClosed(close);
        repo.save(item);
    }
}
