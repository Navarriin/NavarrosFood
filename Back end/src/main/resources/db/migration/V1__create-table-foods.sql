CREATE TABLE foods (
    id INTEGER PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(300) NOT NULL,
    image TEXT NOT NULL,
    value_food REAL NOT NULL,
    status VARCHAR(20)
);