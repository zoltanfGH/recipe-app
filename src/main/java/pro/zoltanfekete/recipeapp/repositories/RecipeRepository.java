package pro.zoltanfekete.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.zoltanfekete.recipeapp.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
