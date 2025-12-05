CREATE TABLE IF NOT EXISTS transactions (
    id UUID PRIMARY KEY,
    client_id VARCHAR(100) NOT NULL,
    title VARCHAR(200) NOT NULL,
    amount NUMERIC(19,2) NOT NULL CHECK (amount > 0),
    date DATE NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_transactions_client_id ON transactions(client_id);
