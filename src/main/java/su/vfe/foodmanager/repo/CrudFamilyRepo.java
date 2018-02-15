package su.vfe.foodmanager.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import su.vfe.foodmanager.model.Family;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudFamilyRepo extends JpaRepository<Family, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Family f WHERE f.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Family save(Family family);

    @Override
    Optional<Family> findById(Integer id);

    @Override
    List<Family> findAll(Sort sort);

    @Query("SELECT f FROM Family f WHERE f.id=:id")
    Family getWithUsers(@Param("id") int id);
}
