ALTER TABLE expenses
    ADD COLUMN IF NOT EXISTS category VARCHAR(100),
    ADD COLUMN IF NOT EXISTS tags VARCHAR(500);

CREATE INDEX IF NOT EXISTS idx_expenses_client_id_category
    ON expenses(client_id, category);
