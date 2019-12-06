package pro.zoltanfekete.recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pro.zoltanfekete.recipeapp.services.RecipeService;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","index","index.html"})
    public String index(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
