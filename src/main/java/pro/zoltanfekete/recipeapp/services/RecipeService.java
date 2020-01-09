package pro.zoltanfekete.recipeapp.services;

import pro.zoltanfekete.recipeapp.commands.RecipeCommand;
import pro.zoltanfekete.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
