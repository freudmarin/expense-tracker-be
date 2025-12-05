ALTER TABLE transactions
    ADD COLUMN IF NOT EXISTS category VARCHAR(100),
    ADD COLUMN IF NOT EXISTS tags VARCHAR(500);

CREATE INDEX IF NOT EXISTS idx_transactions_client_id_category
    ON transactions(client_id, category);
