-- 献立管理アプリ テーブル定義 (PostgreSQL)

-- 1. 週間献立親テーブル
CREATE TABLE meal_plans (
    id SERIAL PRIMARY KEY,
    plan_date DATE NOT NULL UNIQUE,
    day_of_week VARCHAR(10) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 2. 食事テーブル (朝・昼・夕)
CREATE TABLE meals (
    id SERIAL PRIMARY KEY,
    meal_plan_id INTEGER NOT NULL REFERENCES meal_plans(id) ON DELETE CASCADE,
    meal_type VARCHAR(20) NOT NULL, -- BREAKFAST, LUNCH, DINNER
    title VARCHAR(255) NOT NULL,
    total_calories INTEGER DEFAULT 0,
    protein DECIMAL(5, 2) DEFAULT 0.0,
    fat DECIMAL(5, 2) DEFAULT 0.0,
    carbohydrates DECIMAL(5, 2) DEFAULT 0.0,
    CONSTRAINT unique_meal_per_plan UNIQUE (meal_plan_id, meal_type)
);

-- 3. 構成アイテムテーブル (鯖の塩焼き、味噌汁など)
CREATE TABLE meal_items (
    id SERIAL PRIMARY KEY,
    meal_id INTEGER NOT NULL REFERENCES meals(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    sort_order INTEGER DEFAULT 0
);

-- 4. タグテーブル (高タンパク、和食など)
CREATE TABLE meal_tags (
    id SERIAL PRIMARY KEY,
    meal_id INTEGER NOT NULL REFERENCES meals(id) ON DELETE CASCADE,
    tag_name VARCHAR(50) NOT NULL
);

-- インデックス作成
CREATE INDEX idx_meal_plans_date ON meal_plans(plan_date);
CREATE INDEX idx_meals_plan_id ON meals(meal_plan_id);

-- 更新日時自動更新用ファンクション
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_meal_plans_modtime
    BEFORE UPDATE ON meal_plans
    FOR EACH ROW EXECUTE PROCEDURE update_timestamp();