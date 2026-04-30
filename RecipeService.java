package com.aimealplan.service;

import com.aimealplan.entity.Recipe;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    
    Recipe createRecipe(Recipe recipe);
    
    List<Recipe> getAllRecipes();
    
    Optional<Recipe> getRecipeById(Long id);
    
    Recipe updateRecipe(Long id, Recipe recipeDetails);
    
    void deleteRecipe(Long id);
    
    List<Recipe> searchRecipesByTitle(String keyword);
}