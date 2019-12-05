package pro.zoltanfekete.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.zoltanfekete.recipeapp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
