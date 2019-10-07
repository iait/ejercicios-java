create table provincias(
    id bigint not null,
    nombre varchar(200) not null,
    primary key(id)
);

insert into provincias values (1, 'Santa Fe');
insert into provincias values (2, 'Córdoba');
insert into provincias values (3, 'Buenos Aires');