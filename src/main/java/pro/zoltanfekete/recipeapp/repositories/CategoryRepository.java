package pro.zoltanfekete.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.zoltanfekete.recipeapp.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
