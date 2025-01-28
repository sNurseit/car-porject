CREATE TABLE cars (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INT NOT NULL CHECK (year BETWEEN 1886 AND EXTRACT(YEAR FROM CURRENT_DATE)),
    price NUMERIC(15, 2) NOT NULL CHECK (price > 0),
    vin CHAR(17) NOT NULL UNIQUE
);


INSERT INTO cars (make, model, year, price, vin)
VALUES ('Toyota', 'Corolla', 2020, 20000.00, 'JTDBU4EE9A9123456');

INSERT INTO cars (make, model, year, price, vin)
VALUES ( 'Honda', 'Civic', 2022, 22000.00, '2HGFB2F59CH567890');

INSERT INTO cars (make, model, year, price, vin)
VALUES ( 'Ford', 'Mustang', 2019, 35000.00, '1FA6P8CF6K5178912');

INSERT INTO cars (make, model, year, price, vin)
VALUES ('Tesla', 'Model 3', 2023, 45000.00, '5YJ3E1EA7MF123456');

INSERT INTO cars (make, model, year, price, vin)
VALUES ('Chevrolet', 'Impala', 2018, 27000.00, '2G1WT5E37J9112345');
