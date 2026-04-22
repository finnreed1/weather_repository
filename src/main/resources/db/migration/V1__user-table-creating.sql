CREATE SCHEMA IF NOT EXISTS weather_repository;

CREATE TABLE weather_repository.user(
    id SERIAL PRIMARY KEY,
    login VARCHAR(128),
    password VARCHAR(128)
)