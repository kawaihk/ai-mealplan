package com.aimealplan.domain.validation;

import com.aimealplan.domain.model.UserGoal;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PfcRatioSumValidator implements ConstraintValidator<PfcRatioSum, UserGoal> {
    @Override
    public boolean isValid(UserGoal goal, ConstraintValidatorContext context) {
        if (goal == null) {
            return true;
        }
        int p = goal.getProteinRatio() != null ? goal.getProteinRatio() : 0;
        int f = goal.getFatRatio() != null ? goal.getFatRatio() : 0;
        int c = goal.getCarbohydrateRatio() != null ? goal.getCarbohydrateRatio() : 0;
        return (p + f + c) == 100;
    }
}