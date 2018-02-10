package su.vfe.foodmanager.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import su.vfe.foodmanager.model.Item;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ItemRepoImpl implements ItemRepo {

    @Autowired
    private CrudItemRepo crudItemRepo;

    @Autowired
    private CrudFamilyRepo crudFamilyRepo;

    @Override
    public Item get(int id, int familyId) {
        return crudItemRepo.findById(id).filter(item -> item.getFamily().getId() == familyId).orElse(null);
    }

    @Override
    public List<Item> getAll(int familyId) {
        return crudItemRepo.getAll(familyId);
    }

    @Override
    public List<Item> getByStatus(boolean closed, int familyId) {
        return crudItemRepo.getByStatus(closed, familyId);
    }

    @Override
    public List<Item> getBetweenByStatus(LocalDateTime startDate, LocalDateTime endDate, boolean closed, int familyId) {
        return crudItemRepo.getBetweenByStatus(startDate, endDate, closed, familyId);
    }

    @Override
    @Transactional
    public Item save(Item item, int familyId) {
        if (!item.isNew() && get(item.getId(), familyId) == null) {
            return null;
        }
        item.setFamily(crudFamilyRepo.getOne(familyId));
        return crudItemRepo.save(item);
    }

    @Override
    public boolean delete(int id, int familyId) {
        return crudItemRepo.delete(id, familyId) != 0;
    }
}
