-- Create categories table
CREATE TABLE IF NOT EXISTS categories (
    id UUID PRIMARY KEY,
    client_id VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL
);

-- Unique name per client
CREATE UNIQUE INDEX IF NOT EXISTS uk_categories_client_name
    ON categories(client_id, name);

CREATE INDEX IF NOT EXISTS idx_categories_client_id
    ON categories(client_id);

-- Add nullable category_id to expenses for backward compatibility
ALTER TABLE expenses
    ADD COLUMN IF NOT EXISTS category_id UUID;

-- Add FK (set null on delete) and index
ALTER TABLE expenses
    ADD CONSTRAINT IF NOT EXISTS fk_expenses_category
        FOREIGN KEY (category_id)
        REFERENCES categories (id)
        ON DELETE SET NULL;

