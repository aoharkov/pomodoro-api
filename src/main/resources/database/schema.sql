DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS pomodoros CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    id    BIGSERIAL PRIMARY KEY,
    nickname  VARCHAR(30) NOT NULL,
    password VARCHAR(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS pomodoros
(
    id    BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    description  VARCHAR(250),
    length INT NOT NULL,
    complited_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS roles
(
    id    BIGSERIAL PRIMARY KEY,
    name varchar(30) not null
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);
