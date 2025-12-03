CREATE TABLE IF NOT EXISTS expenses (
    id UUID PRIMARY KEY,
    client_id VARCHAR(100) NOT NULL,
    title VARCHAR(200) NOT NULL,
    amount NUMERIC(19,2) NOT NULL CHECK (amount > 0),
    date DATE NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_expenses_client_id ON expenses(client_id);
