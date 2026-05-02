package com.aimealplan.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meal_plans")
@Getter  
@Setter  
@ToString(exclude = {"user", "meals"})  
@EqualsAndHashCode(exclude = {"user", "meals"})  
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;

    private String goal; // 例: ダイエット、筋肥大など

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL)
    private List<Meal> meals;
}