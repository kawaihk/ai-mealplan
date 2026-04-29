package com.aimealplan.repository;

import com.aimealplan.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByMealPlanId(Long mealPlanId);
}