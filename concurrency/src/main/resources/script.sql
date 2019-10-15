CREATE TABLE test (
    id bigint NOT NULL PRIMARY KEY,
    value bigint NOT NULL
);
CREATE TABLE personas (
    id bigint NOT NULL PRIMARY KEY,
    value varchar(200) NOT NULL
);
CREATE TABLE id_gen (
    gen_name varchar(80) PRIMARY KEY,
    gen_val bigint
);
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;