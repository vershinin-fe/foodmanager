package su.vfe.foodmanager.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import su.vfe.foodmanager.model.Item;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudItemRepo extends JpaRepository<Item, Integer> {
    @Override
    Optional<Item> findById(Integer id);

    @Override
    List<Item> findAll(Sort sort);

    @Query("SELECT i from Item i WHERE i.closed=:closed ORDER BY i.createDate")
    List<Item> getByStatus(@Param("closed") boolean closed);

    @Query("SELECT i from Item i WHERE i.closed=:closed AND i.createDate BETWEEN :startDate AND :endDate ORDER BY i.createDate")
    List<Item> getBetweenByStatus(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("closed") boolean closed);

    @Override
    @Transactional
    Item save(Item item);

    @Modifying
    @Transactional
    @Query("DELETE FROM Item i WHERE i.id=:id")
    int delete(@Param("id") int id);
}
