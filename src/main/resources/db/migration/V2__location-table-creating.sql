CREATE SCHEMA IF NOT EXISTS weather_repository;

CREATE TABLE weather_repository.location(
    id SERIAL PRIMARY KEY,
    name VARCHAR(128),
    user_id INT REFERENCES "user"(id),
    latitude DECIMAL,
    longitude DECIMAL
);