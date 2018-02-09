package su.vfe.foodmanager.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import su.vfe.foodmanager.model.Item;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ItemRepoImpl implements ItemRepo {
    private static final Sort SORT_CREATE_DATE = new Sort(Sort.Direction.ASC, "createDate");

    @Autowired
    private CrudItemRepo crudRepo;

    @Override
    public Item get(int id) {
        return crudRepo.findById(id).orElse(null);
    }

    @Override
    public List<Item> getAll() {
        return crudRepo.findAll(SORT_CREATE_DATE);
    }

    @Override
    public List<Item> getByStatus(boolean closed) {
        return crudRepo.getByStatus(closed);
    }

    @Override
    public List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed) {
        return crudRepo.getBetweenByStatus(startDate, endDate, closed);
    }

    @Override
    public Item save(Item item) {
        return crudRepo.save(item);
    }

    @Override
    public boolean delete(int id) {
        return crudRepo.delete(id) != 0;
    }
}
