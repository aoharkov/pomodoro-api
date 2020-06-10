DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS spheres CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS pomodoros CASCADE;
DROP TYPE IF EXISTS e_role CASCADE;
DROP TYPE IF EXISTS e_status CASCADE;

CREATE TYPE e_role AS ENUM (
    'ADMIN',
    'USER'
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(320) NOT NULL UNIQUE,
    nickname VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    role e_role NOT NULL,
    registration_date TIMESTAMP WITH TIME ZONE NOT NULL,
    storage JSON,
    default_length INT NOT NULL
);

CREATE TABLE IF NOT EXISTS spheres (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    sphere_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (sphere_id) REFERENCES spheres(id) ON DELETE RESTRICT
);

CREATE TYPE e_status AS ENUM (
    'STARTED',
    'PAUSED',
    'FINISHED'
);

CREATE TABLE IF NOT EXISTS pomodoros (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    description VARCHAR(250),
    declared_length INT NOT NULL,
    tracked_length INT NOT NULL,
    status e_status NOT NULL,
    started_date_time TIMESTAMP WITH TIME ZONE,
    last_start TIMESTAMP WITH TIME ZONE,
    finished_date_time TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);
