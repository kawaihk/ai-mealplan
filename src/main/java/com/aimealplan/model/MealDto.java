package com.aimealplan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDto {
    private Long id;
    private Long mealPlanId;
    private LocalDate mealDate;
    private String mealType; // BREAKFAST, LUNCH, DINNER, SNACK
    private String recipeName;
    private String instructions;
    private Integer calories;
    private Integer protein;
}