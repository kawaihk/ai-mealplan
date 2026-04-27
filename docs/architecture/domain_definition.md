# ドメイン定義 (Domain Definition)

## 1. 境界づけられたコンテキスト: 献立管理

### エンティティ (Entities)
- **MealPlan (週間献立)**
    - 識別子: `plan_id`
    - 属性: `date` (日付), `day_of_week` (曜日)
    - 制約: 1日に対して1つのMealPlanが存在する。

- **Meal (食事内容)**
    - 識別子: `meal_id`
    - 属性: `meal_type` (朝食/昼食/夕食), `title` (メイン料理名)
    - 振る舞い: 合計栄養素の算出。

### 値オブジェクト (Value Objects)
- **Nutrients (栄養素バランス)**
    - 属性: `calories`, `protein`, `fat`, `carbohydrates`
    - 特徴: 不変オブジェクト。PFCバランスの計算ロジックを持つ。

- **MealItem (構成メニュー)**
    - 属性: `name` (料理を構成する品目、例: 納豆、味噌汁)

### 列挙型 (Enums)
- **MealType**: `BREAKFAST`, `LUNCH`, `DINNER`

## 2. 業務ルール
1. 1つの `MealPlan` は最大3つの `Meal` (朝・昼・夕) を持つことができる。
2. 各 `Meal` には複数の `MealItem` と `Tag` が紐づく。
3. 栄養素は `Meal` 単位で保持し、`MealPlan` (1日合計) で集計可能とする。

## 3. Spring Boot パッケージ構成案
```text
com.example.mealplan
 ├── domain
 │    ├── model (Entities, Value Objects)
 │    ├── repository (Interfaces)
 │    └── service (Business Logic)
 ├── infrastructure (DB Implementations)
 └── web (Controllers, DTOs)
```