package com.aimealplan.domain.model;

import com.aimealplan.domain.validation.PfcRatioSum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "user_goals")
@PfcRatioSum
@Data
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Min(0)
    private Double targetWeight;

    private String activityLevel;

    @Min(0)
    private Integer targetCalories;

    @Min(0) @Max(100)
    private Integer proteinRatio;

    @Min(0) @Max(100)
    private Integer fatRatio;

    @Min(0) @Max(100)
    private Integer carbohydrateRatio;
}