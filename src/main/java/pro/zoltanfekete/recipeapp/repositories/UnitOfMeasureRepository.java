package pro.zoltanfekete.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.zoltanfekete.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
