package com.aimealplan.service.impl;

import com.aimealplan.entity.Recipe;
import com.aimealplan.repository.RecipeRepository;
import com.aimealplan.exception.ResourceNotFoundException;
import com.aimealplan.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    @Transactional
    public Recipe updateRecipe(Long id, Recipe recipeDetails) {
        return recipeRepository.findById(id)
            .map(recipe -> {
                recipe.setTitle(recipeDetails.getTitle());
                recipe.setDescription(recipeDetails.getDescription());
                recipe.setCalories(recipeDetails.getCalories());
                return recipeRepository.save(recipe);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> searchRecipesByTitle(String keyword) {
        return recipeRepository.findByTitleContaining(keyword);
    }
}