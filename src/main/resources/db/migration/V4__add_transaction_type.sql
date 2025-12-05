-- Add `type` column to transactions and enforce allowed values
ALTER TABLE transactions
    ADD COLUMN IF NOT EXISTS type VARCHAR(10) NOT NULL DEFAULT 'expense';

-- Add check constraint to allow only 'income' or 'expense'
ALTER TABLE transactions
    ADD CONSTRAINT IF NOT EXISTS chk_transactions_type_allowed
    CHECK (type IN ('income', 'expense'));

-- Index for queries filtering by type
CREATE INDEX IF NOT EXISTS idx_transactions_client_id_type
    ON transactions(client_id, type);
