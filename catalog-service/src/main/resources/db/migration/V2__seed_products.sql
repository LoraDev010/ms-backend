INSERT INTO products (id, name, price, currency)
VALUES ('11111111-1111-1111-1111-111111111111', 'Demo product', 10.00, 'USD')
ON CONFLICT (id) DO NOTHING;
