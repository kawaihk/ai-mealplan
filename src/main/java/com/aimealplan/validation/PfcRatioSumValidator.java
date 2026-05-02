package com.aimealplan.validation;

import com.aimealplan.model.UserGoalDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PfcRatioSumValidator implements ConstraintValidator<PfcRatioSum, UserGoalDto> {
    @Override
    public boolean isValid(UserGoalDto goal, ConstraintValidatorContext context) {
        if (goal == null) {
            return true;
        }
        int p = goal.getProteinRatio() != null ? goal.getProteinRatio() : 0;
        int f = goal.getFatRatio() != null ? goal.getFatRatio() : 0;
        int c = goal.getCarbohydrateRatio() != null ? goal.getCarbohydrateRatio() : 0;
        return (p + f + c) == 100;
    }
}