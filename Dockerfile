FROM postgres:12.3-alpine
COPY ./src/main/resources/database/dump.sql /docker-entrypoint-initdb.d/
