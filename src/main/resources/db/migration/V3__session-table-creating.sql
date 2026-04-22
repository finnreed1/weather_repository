CREATE SCHEMA IF NOT EXISTS weather_repository;

CREATE TABLE weather_repository.session(
    id SERIAL,
    ser_id INT REFERENCES "user"(id),
    expires_at TIMESTAMP
)