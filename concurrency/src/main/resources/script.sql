CREATE TABLE test (
    id bigint NOT NULL PRIMARY KEY,
    value bigint NOT NULL
);
CREATE TABLE personas (
    id bigint NOT NULL PRIMARY KEY,
    value varchar(200) NOT NULL
);
CREATE TABLE id_gen (
    gen_key varchar(100) PRIMARY KEY,
    gen_value bigint
);
CREATE SEQUENCE test_sequence START WITH 20 INCREMENT BY 80;