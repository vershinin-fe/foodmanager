package su.vfe.foodmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import su.vfe.foodmanager.model.Item;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudItemRepo extends JpaRepository<Item, Integer> {

    @Query("SELECT i FROM Item i WHERE i.family.id=:familyId ORDER BY i.createDate ASC")
    List<Item> getAll(@Param("familyId") int familyId);

    @Query("SELECT i from Item i WHERE i.closed=:closed AND i.family.id=:familyId ORDER BY i.createDate")
    List<Item> getByStatus(@Param("closed") boolean closed, @Param("familyId") int familyId);

    @Query("SELECT i from Item i WHERE i.closed=:closed AND i.family.id=:familyId AND i.createDate BETWEEN :startDate AND :endDate ORDER BY i.createDate")
    List<Item> getBetweenByStatus(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("closed") boolean closed, @Param("familyId") int familyId);

    @Override
    @Transactional
    Item save(Item item);

    @Modifying
    @Transactional
    @Query("DELETE FROM Item i WHERE i.id=:id AND i.family.id=:familyId")
    int delete(@Param("id") int id, @Param("familyId") int familyId);
}