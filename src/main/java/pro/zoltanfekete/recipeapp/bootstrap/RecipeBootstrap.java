package pro.zoltanfekete.recipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pro.zoltanfekete.recipeapp.model.*;
import pro.zoltanfekete.recipeapp.repositories.CategoryRepository;
import pro.zoltanfekete.recipeapp.repositories.RecipeRepository;
import pro.zoltanfekete.recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
    }

    //Get UOMs
    private UnitOfMeasure getUnitOfMeasure(String description){
        return unitOfMeasureRepository
                .findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Unit of measure " + description + " not found"));
    }

    //Get Categories
    private Category getCategory(String description) {
        return categoryRepository
                .findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Category" + description + " not found"));
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        //Get UOM Optinals
        UnitOfMeasure teaspoonUOM = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure tablespoonUOM = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure cupUOM = getUnitOfMeasure("Cup");
        UnitOfMeasure pinchUOM = getUnitOfMeasure("Pinch");
        UnitOfMeasure ounceUOM = getUnitOfMeasure("Ounce");
        UnitOfMeasure dashUOM = getUnitOfMeasure("Dash");
        UnitOfMeasure pintUOM = getUnitOfMeasure("Pint");
        UnitOfMeasure eachUOM =getUnitOfMeasure("Each");
        UnitOfMeasure cloveUOM = getUnitOfMeasure("Clove");

        //Get Category Optionals
        Category americanCategory = getCategory("American");
        Category mexicanCategory = getCategory("Mexican");

        //Perfect Guacamole
        Recipe perfectGuacamoleRecipe = new Recipe();

        //Setting Perfect Guacamole
        perfectGuacamoleRecipe.setDescription("Perfect Guacamole");
        perfectGuacamoleRecipe.setCookTime(0);
        perfectGuacamoleRecipe.setPrepTime(10);
        perfectGuacamoleRecipe.setDifficulty(Difficulty.EASY);
        perfectGuacamoleRecipe.setServings(2);
        perfectGuacamoleRecipe.setSource("Simply Recipes");
        //perfectGuacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        Notes perfectGuacamoleNote = new Notes();
        perfectGuacamoleNote.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");

        perfectGuacamoleRecipe.setNotes(perfectGuacamoleNote);

        //Setting Perfect Guacamole Ingredients
        perfectGuacamoleRecipe.addIngredient(new Ingredient("ripe avocados",new BigDecimal(2),eachUOM));
        perfectGuacamoleRecipe.addIngredient(new Ingredient("Kosher salt",new BigDecimal(".5"),teaspoonUOM));
        perfectGuacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice",new BigDecimal(1),tablespoonUOM));
        perfectGuacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(2),tablespoonUOM));
        perfectGuacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced",new BigDecimal(2),eachUOM));
        perfectGuacamoleRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped",new BigDecimal(2),tablespoonUOM));
        perfectGuacamoleRecipe.addIngredient(new Ingredient(" freshly grated black pepper",new BigDecimal(1),dashUOM));
        perfectGuacamoleRecipe.addIngredient(new Ingredient(" ripe tomato, seeds and pulp removed, chopped",new BigDecimal(".5"),eachUOM));

        //Setting Perfect Guacamole Categories
        perfectGuacamoleRecipe.getCategories().add(mexicanCategory);
        perfectGuacamoleRecipe.getCategories().add(americanCategory);

        //Adding Perfect Guacamole to Recipes List
        recipes.add(perfectGuacamoleRecipe);

        //Spicy Grilled Chicken Tacos
        Recipe spicyGrilledChickenTacosRecipe = new Recipe();

        //Setting Spicy Grilled Chicken Tacos
        spicyGrilledChickenTacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTacosRecipe.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacosRecipe.setPrepTime(20);
        spicyGrilledChickenTacosRecipe.setCookTime(15);
        spicyGrilledChickenTacosRecipe.setServings(4);
        spicyGrilledChickenTacosRecipe.setSource("Simply Recipes");
        //spicyGrilledChickenTacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos");
        spicyGrilledChickenTacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");

        Notes spicyGrilledChickenTacosNotes = new Notes();
        spicyGrilledChickenTacosNotes.setRecipeNotes("Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");

        spicyGrilledChickenTacosRecipe.setNotes(spicyGrilledChickenTacosNotes);

        //Setting Spicy Grilled Chicken Tacos Ingredients
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("ancho chili powder",new BigDecimal(2),tablespoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("dried oregano",new BigDecimal(1),teaspoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("dried cumin",new BigDecimal(1),teaspoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("sugar",new BigDecimal(1),teaspoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("salt",new BigDecimal(".5"),teaspoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("garlic, finely chopped",new BigDecimal(1),cloveUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("finely grated orange zest",new BigDecimal(1),tablespoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice",new BigDecimal(3),tablespoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("olive oil",new BigDecimal(2),tablespoonUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs",new BigDecimal(6),eachUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("small corn tortillas",new BigDecimal(8),eachUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("packed baby arugula",new BigDecimal(3),cupUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced",new BigDecimal(2),eachUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced",new BigDecimal(4),eachUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved",new BigDecimal("1.5"),pintUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced",new BigDecimal(".75"),eachUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro",new BigDecimal(1),eachUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("sour cream",new BigDecimal("1.5"),cupUOM));
        spicyGrilledChickenTacosRecipe.addIngredient(new Ingredient("lime, cut into wedges",new BigDecimal(1),eachUOM));

        //Setting Spicy Grilled Chicken Tacos Categories
        spicyGrilledChickenTacosRecipe.getCategories().add(mexicanCategory);
        spicyGrilledChickenTacosRecipe.getCategories().add(americanCategory);

        //Adding Spicy Grilled Chicken Tacos List
        recipes.add(spicyGrilledChickenTacosRecipe);

        return recipes;
    }

}
