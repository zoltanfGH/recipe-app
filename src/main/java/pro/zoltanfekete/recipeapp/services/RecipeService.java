package pro.zoltanfekete.recipeapp.services;

import pro.zoltanfekete.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
